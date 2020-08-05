package com.amon.javacore.disruptor.common;

import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/3.
 */
public class CommonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LoggerFactory.getLogger(t.getName()).error(e.getMessage(), e);
            }
        });
        return t;
    }
}
