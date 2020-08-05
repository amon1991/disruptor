package com.amon.javacore.disruptor.heigh.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/6.
 */
public class Consumer implements WorkHandler<Order> {

    private String comsumerId;

    private AtomicInteger count = new AtomicInteger(0);

    private Random random = new Random();

    public Consumer(String comsumerId) {
        this.comsumerId = comsumerId;
    }

    @Override
    public void onEvent(Order event) throws Exception {

        Thread.sleep(random.nextInt(5));
        System.out.println("Current consumer:" + this.comsumerId + ",messageId:" + event.getId());
        count.incrementAndGet();

    }

    public int getCount() {
        return count.get();
    }

}
