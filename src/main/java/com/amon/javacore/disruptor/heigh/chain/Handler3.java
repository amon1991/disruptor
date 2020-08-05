package com.amon.javacore.disruptor.heigh.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/4.
 */
public class Handler3 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handler3: PRINT INFO:" + event.toString());
    }
}
