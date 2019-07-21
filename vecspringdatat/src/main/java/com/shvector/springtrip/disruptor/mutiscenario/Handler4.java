/**
 * @create 2019-07-14 09:35
 * @desc Handler4
 **/
package com.shvector.springtrip.disruptor.mutiscenario;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Handler4 implements EventHandler<Trade>,WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        //System.out.println("handler4: get name : " + event.getName());
        //event.setName(event.getName() + "h4");
    }
}

