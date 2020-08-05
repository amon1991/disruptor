package com.amon.javacore.disruptor.heigh.multi;

import com.lmax.disruptor.RingBuffer;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/8.
 */
public class Producer {

    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(String uuId) {

        // 1. get a squence from ringbuffer
        long squenece = ringBuffer.next();
        try {
            // 2. get empty OrderEvent from squenece
            Order event = ringBuffer.get(squenece);

            // 3. set value for message event
            event.setId(uuId);

        } finally {
            // 4. publish data
            ringBuffer.publish(squenece);
        }

    }

}
