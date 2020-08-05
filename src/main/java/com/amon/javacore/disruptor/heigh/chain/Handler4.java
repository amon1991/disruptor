package com.amon.javacore.disruptor.heigh.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/4.
 */
public class Handler4 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        event.setPrice("20");
        System.out.println("Handler4: Price:" + event.getPrice());
    }
}
