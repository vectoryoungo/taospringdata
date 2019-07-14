/**
 * @create 2019-07-14 11:03
 * @desc LongEventFactory
 **/
package com.shvector.springtrip.disruptor.demoLong;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

