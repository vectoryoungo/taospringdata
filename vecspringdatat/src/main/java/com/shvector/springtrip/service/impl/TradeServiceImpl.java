/**
 * @create 2019-07-08 11:40
 * @desc implement TradeService
 **/
package com.shvector.springtrip.service.impl;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.shvector.springtrip.disruptor.TradeEvent;
import com.shvector.springtrip.disruptor.TradeEventFactory;
import com.shvector.springtrip.disruptor.TradeEventHandler;
import com.shvector.springtrip.disruptor.Translator;
import com.shvector.springtrip.dto.TradeDTO;
import com.shvector.springtrip.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TradeServiceImpl implements TradeService{


    @Autowired
    private TradeEventFactory tradeEventFactory;
    @Autowired
    private EventHandler<TradeEvent> tradeEventHandler;
    @Autowired
    private Translator translator;
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
    private Disruptor<TradeEvent> disruptor = new Disruptor<TradeEvent>(tradeEventFactory,ringBufferSize,executorService, ProducerType.SINGLE,new YieldingWaitStrategy());

    //EventHandler<TradeEvent> tradeEventHandler = new TradeEventHandler();

    public void order(TradeDTO tradeDTO) {

        if (null == tradeDTO) {
            return;
        }

        disruptor.handleEventsWith(tradeEventHandler);
        disruptor.start();
        // 发布事件；
        RingBuffer<TradeEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(translator, tradeDTO);
    }
}

