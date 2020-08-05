package com.amon.javacore.disruptor.quickstart;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/2.
 */
public class OrderEvent {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
