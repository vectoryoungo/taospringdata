/**
 * @create 2019-07-08 15:20
 * @desc trade event handler
 **/
package com.shvector.springtrip.disruptor.demoi;

import com.lmax.disruptor.EventHandler;

public class MyTradeEventHandler implements EventHandler<MyTradeEvent> {

    public void onEvent(MyTradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        //撮合逻辑需要在这里实现
        System.out.println(" 买家..." + event.getBuyer().get(0).getUserId());
        System.out.println(" 卖家..." + event.getSeller().get(0).getUserId());
        System.out.println(" 买家数量..."+event.getBuyer().size());
        System.out.println(" 卖家数量..."+event.getSeller().size());
    }
}

