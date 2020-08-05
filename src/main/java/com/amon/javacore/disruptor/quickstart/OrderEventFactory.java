package com.amon.javacore.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/2.
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
