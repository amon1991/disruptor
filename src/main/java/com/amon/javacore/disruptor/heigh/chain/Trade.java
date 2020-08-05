package com.amon.javacore.disruptor.heigh.chain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/4.
 */
public class Trade {

    private String id;
    private String name;
    private String price;
    private AtomicInteger count = new AtomicInteger(0);


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
