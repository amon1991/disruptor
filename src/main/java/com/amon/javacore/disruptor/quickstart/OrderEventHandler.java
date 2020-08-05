package com.amon.javacore.disruptor.quickstart;


import com.lmax.disruptor.EventHandler;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/2.
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Consumer:" + orderEvent.getValue());
    }

}
