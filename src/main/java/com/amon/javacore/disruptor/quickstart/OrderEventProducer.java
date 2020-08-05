package com.amon.javacore.disruptor.quickstart;

import com.lmax.disruptor.RingBuffer;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/3.
 */
public class OrderEventProducer {

    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(long value) {

        // 1. get a squence from ringbuffer
        long squenece = ringBuffer.next();
        try {
            // 2. get empty OrderEvent from squenece
            OrderEvent event = ringBuffer.get(squenece);

            // 3. set value for message event
            event.setValue(value);
        } finally {
            // 4. publish data
            ringBuffer.publish(squenece);
        }

    }


}
