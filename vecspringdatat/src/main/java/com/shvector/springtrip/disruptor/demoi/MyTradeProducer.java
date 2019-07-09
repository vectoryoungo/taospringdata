/**
 * @create 2019-07-08 15:15
 * @desc producer
 **/
package com.shvector.springtrip.disruptor.demoi;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.util.List;

public class MyTradeProducer {

    private RingBuffer<MyTradeEvent> ringBuffer;

    public MyTradeProducer(RingBuffer<MyTradeEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg TRANSLATOR = new EventTranslatorOneArg<MyTradeEvent,MyData>() {

        public void translateTo(MyTradeEvent event, long sequence, MyData arg0) {
            event.setBuyer(arg0.getBuyer());
            event.setSeller(arg0.getSeller());
        }
    };

    public void onData(final MyData value) {
        ringBuffer.publishEvent(TRANSLATOR,value);
    }
}

