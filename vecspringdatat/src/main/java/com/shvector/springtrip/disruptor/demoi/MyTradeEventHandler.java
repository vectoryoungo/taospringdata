/**
 * @create 2019-07-08 15:20
 * @desc trade event handler
 **/
package com.shvector.springtrip.disruptor.demoi;

import com.lmax.disruptor.EventHandler;

public class MyTradeEventHandler implements EventHandler<MyTradeEvent> {

    public void onEvent(MyTradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        //撮合
        System.out.println(" 撮合进行..." + event.getUserId());
        event.getTxType();
        event.getBusinessType();


    }
}

