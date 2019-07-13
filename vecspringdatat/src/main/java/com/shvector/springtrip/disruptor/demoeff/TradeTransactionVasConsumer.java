/**
 * @create 2019-07-12 16:51
 * @desc TradeTransactionVasConsumer
 **/
package com.shvector.springtrip.disruptor.demoeff;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        //do something....
        System.out.println("TradeTransaction id " + event.getId());
        System.out.println("TradeTransaction price " + event.getPrice());
    }
}

