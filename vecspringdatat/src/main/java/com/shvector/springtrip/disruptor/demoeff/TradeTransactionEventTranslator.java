/**
 * @create 2019-07-12 16:50
 * @desc TradeTransactionEventTranslator
 **/
package com.shvector.springtrip.disruptor.demoeff;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

public class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {
    private Random random=new Random();
    @Override
    public void translateTo(TradeTransaction event, long sequence) {
        this.generateTradeTransaction(event);
    }
    private TradeTransaction generateTradeTransaction(TradeTransaction trade){
        trade.setPrice(random.nextDouble()*9999);
        return trade;
    }
}

