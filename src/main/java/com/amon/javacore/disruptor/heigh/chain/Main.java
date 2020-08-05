package com.amon.javacore.disruptor.heigh.chain;

import com.amon.javacore.disruptor.common.CommonThreadFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/4.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        // 1. create disruptor
        ExecutorService es = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        int ringBufferSize = 1024 * 1024;
        ThreadFactory threadFactory = new CommonThreadFactory();

        Disruptor<Trade> disruptor = new Disruptor<>(
                () -> new Trade(),
                ringBufferSize,
                threadFactory,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        // 2. set consumers
        /*disruptor.handleEventsWith(new Handler1())
                .handleEventsWith(new Handler2())
                .handleEventsWith(new Handler3());*/

        /*disruptor.handleEventsWith(new Handler1());
        disruptor.handleEventsWith(new Handler2());
        disruptor.handleEventsWith(new Handler3());*/

        /*EventHandlerGroup<Trade> group = disruptor.handleEventsWith(new Handler1(), new Handler2());
        group.then(new Handler3());*/

        Handler1 h1 = new Handler1();
        Handler2 h2 = new Handler2();
        Handler3 h3 = new Handler3();
        Handler4 h4 = new Handler4();
        Handler5 h5 = new Handler5();

        disruptor.handleEventsWith(h1, h4);

        disruptor.after(h1).handleEventsWith(h2);
        disruptor.after(h4).handleEventsWith(h5);
        disruptor.after(h2, h4).handleEventsWith(h3);

        // 3. start disruptor
        disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);

        long bgTime = System.currentTimeMillis();

        es.submit(new TradePublisher(disruptor, latch));

        latch.await();

        disruptor.shutdown();
        es.shutdown();
        System.out.println("Use time:" + (System.currentTimeMillis() - bgTime) + "ms");

    }
}
