package com.amon.javacore.disruptor.quickstart;

import com.amon.javacore.disruptor.common.CommonThreadFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/2.
 */
public class Main {
    public static void main(String[] args) {

        // 1. create disruptor instance
        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int ringBufferSize = 1024 * 1024;
        ThreadFactory threadFactory = new CommonThreadFactory();

        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                orderEventFactory,
                ringBufferSize,
                threadFactory,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        // 2. add listener for disruptor
        disruptor.handleEventsWith(new OrderEventHandler());

        // 3. start disruptor
        disruptor.start();

        // 4. get data buffer instance in the disruptor
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        for (int i = 0; i < 100; i++) {
            producer.sendData(i);
        }

        disruptor.shutdown();

    }
}
