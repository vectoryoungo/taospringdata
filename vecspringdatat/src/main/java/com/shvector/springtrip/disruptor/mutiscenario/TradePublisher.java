/**
 * @create 2019-07-14 09:36
 * @desc TradePublisher
 **/
package com.shvector.springtrip.disruptor.mutiscenario;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.dsl.Disruptor;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;

public class TradePublisher implements Runnable {

    Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static int LOOP=1000000;//模拟百万次交易的发生

    public TradePublisher(CountDownLatch latch,Disruptor<Trade> disruptor) {
        this.disruptor=disruptor;
        this.latch=latch;
    }

    @Override
    public void run() {
        TradeEventTranslator tradeTransloator = new TradeEventTranslator();
        for(int i=0;i<LOOP;i++){
            disruptor.publishEvent(tradeTransloator);
        }
        latch.countDown();
    }
}

class TradeEventTranslator implements EventTranslator<Trade> {

    @Override
    public void translateTo(Trade event, long sequence) {
        this.generateTrade(event);
    }

    private Trade generateTrade(Trade trade){
        Random random=new Random();
        int modNum = random.nextInt(1000) % 2;
        trade.setOrderNo(UUID.randomUUID().toString());
        //trade.setPrice(new BigDecimal(String.valueOf(random.nextInt())));
        trade.setUserId(random.nextLong());
        trade.setAmount(new BigDecimal(random.nextInt(50)));
        if (modNum ==0) {
            trade.setBusinessType(1);
        }else {
            trade.setBusinessType(2);
        }
        trade.setRelationId(random.nextInt());
        trade.setTxType(1);
        trade.setPrice(new BigDecimal((int)(Math.random() * 1000000)));
        return trade;
    }
}

