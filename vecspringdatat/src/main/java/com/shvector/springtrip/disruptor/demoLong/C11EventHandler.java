/**
 * @create 2019-07-14 11:04
 * @desc 该消费者执行将数值+10的操作。可以看到该消费者同时实现了EventHandler和WorkHandler两个接口。如果不需要池化，只需要实现EventHandler类即可。如果需要池化，只需要实现WorkHandler类即可。
 **/
package com.shvector.springtrip.disruptor.demoLong;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class C11EventHandler implements EventHandler<LongEvent>,WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1 consumer finished.number=" + number);
    }
}

