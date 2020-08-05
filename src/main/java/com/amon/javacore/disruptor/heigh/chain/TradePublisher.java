package com.amon.javacore.disruptor.heigh.chain;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/4.
 */
public class TradePublisher implements Runnable {

    private Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static int PUBLISH_COUNT = 1;

    public TradePublisher(Disruptor<Trade> disruptor, CountDownLatch latch) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {

        TradeEventTranslator eventTranslator = new TradeEventTranslator();
        for (int i = 0; i < PUBLISH_COUNT; i++) {
            disruptor.publishEvent(eventTranslator);
        }
        latch.countDown();

    }
}
