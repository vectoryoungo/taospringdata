/**
 * @create 2019-07-14 11:06
 * @desc 该消费者类负责将数值+20.
 **/
package com.shvector.springtrip.disruptor.demoLong;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class C21EventHandler implements EventHandler<LongEvent>,WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number += 20;
        System.out.println(System.currentTimeMillis()+": c2-1 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number += 20;
        System.out.println(System.currentTimeMillis()+": c2-1 consumer finished.number=" + number);
    }
}

