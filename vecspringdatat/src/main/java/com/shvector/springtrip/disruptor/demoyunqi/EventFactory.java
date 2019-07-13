/**
 * @create 2019-07-13 23:16
 * @desc EventFactory
 **/
package com.shvector.springtrip.disruptor.demoyunqi;

public class EventFactory implements com.lmax.disruptor.EventFactory<SeriesDataEvent>{
    @Override
    public SeriesDataEvent newInstance() {
        return new SeriesDataEvent();
    }
}

