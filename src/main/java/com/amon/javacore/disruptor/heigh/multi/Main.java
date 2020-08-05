package com.amon.javacore.disruptor.heigh.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/6.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        RingBuffer<Order> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                new EventFactory<Order>() {
                    @Override
                    public Order newInstance() {
                        return new Order();
                    }
                },
                1024 * 1024,
                new YieldingWaitStrategy()
        );

        // 2. create a barrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        // 3.create multi consumer
        Consumer[] consumers = new Consumer[10];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("Consumer" + i);
        }

        // 4. create multi consumer pool
        WorkerPool<Order> workerPool = new WorkerPool<>(
                ringBuffer,
                sequenceBarrier,
                new EventExceptionHandler(),
                consumers);

        // 5. set squences
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        // 6.  start worker pool
        ExecutorService executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        workerPool.start(executorService);

        final CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            final Producer producer = new Producer(ringBuffer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        producer.sendData(UUID.randomUUID().toString());
                    }
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println("Finish Create Theads,Start to produce data");
        latch.countDown();

        Thread.sleep(10000);

        System.err.println("Task in the consumer3:"  + consumers[2].getCount());

        executorService.shutdown();

    }

    static class EventExceptionHandler implements ExceptionHandler<Order> {
        @Override
        public void handleEventException(Throwable ex, long sequence, Order event) {
        }

        @Override
        public void handleOnStartException(Throwable ex) {
        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
        }
    }
}
