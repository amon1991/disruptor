package com.amon.javacore.disruptor.heigh.chain;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/4.
 */
public class TradeEventTranslator implements EventTranslator<Trade> {

    private Random random = new Random();

    @Override
    public void translateTo(Trade event, long sequence) {
        event.setPrice(random.nextDouble() + "");
    }
}
