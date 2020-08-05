package com.amon.javacore.disruptor.heigh.chain;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/4.
 */
public class Handler2 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handler2: SET ID");
        event.setId(UUID.randomUUID().toString());
    }
}
