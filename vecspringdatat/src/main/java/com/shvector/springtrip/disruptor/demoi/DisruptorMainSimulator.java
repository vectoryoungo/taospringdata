/**
 * @create 2019-07-08 15:21
 * @desc simulator
 **/
package com.shvector.springtrip.disruptor.demoi;

import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DisruptorMainSimulator {

    public static void main(String[] args) {
         ExecutorService executorService = Executors.newFixedThreadPool(4);
         int bufferSize = 1024 * 1024;
        Disruptor<MyTradeEvent> disruptor = new Disruptor<MyTradeEvent>(new MyTradeEventFactory(),
                bufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleExceptionsWith(new IgnoreExceptionHandler());
        disruptor.handleEventsWith(new MyTradeEventHandler());
        RingBuffer<MyTradeEvent> ringBuffer = disruptor.start();
        MyTradeProducer myTradeProducer = new MyTradeProducer(ringBuffer);


        for (int i=0;i<1000000;i++) {

            MyData myData = new MyData();
            myData.setAmount(new BigDecimal(i));
            if (i%2==0) {
                myData.setBusinessType(2);
                myData.setTxType(1);
            }else {
                myData.setBusinessType(1);
                myData.setTxType(2);
            }
            myData.setOrderNo(UUID.randomUUID().toString());
            int random = (int)(Math.random()*100+1);
            myData.setPrice(new BigDecimal(random));
            myData.setRelationId(i);

            myData.setUserId(Long.valueOf(i));
            myTradeProducer.onData(myData);
            try {
                Thread.sleep(1);// wait for task execute....
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        disruptor.shutdown();
        ExecutorsUtils.shutdownAndAwaitTermination(executorService, 60, TimeUnit.SECONDS);
    }
}

