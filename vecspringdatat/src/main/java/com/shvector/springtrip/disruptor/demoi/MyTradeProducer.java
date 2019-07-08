/**
 * @create 2019-07-08 15:15
 * @desc producer
 **/
package com.shvector.springtrip.disruptor.demoi;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class MyTradeProducer {

    private RingBuffer<MyTradeEvent> ringBuffer;

    public MyTradeProducer(RingBuffer<MyTradeEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg TRANSLATOR = new EventTranslatorOneArg<MyTradeEvent,MyData>() {

        public void translateTo(MyTradeEvent event, long sequence, MyData arg0) {
            event.setAmount(arg0.getAmount());
            event.setBusinessType(arg0.getBusinessType());
            event.setOrderNo(arg0.getOrderNo());
            event.setPrice(arg0.getPrice());
            event.setRelationId(arg0.getRelationId());
            event.setTxType(arg0.getTxType());
            event.setUserId(arg0.getUserId());
        }
    };

    public void onData(final MyData value) {
        ringBuffer.publishEvent(TRANSLATOR,value);
    }
}

