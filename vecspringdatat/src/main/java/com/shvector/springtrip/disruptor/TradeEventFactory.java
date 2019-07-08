/**
 * @create 2019-07-08 14:07
 * @desc generate trade event
 **/
package com.shvector.springtrip.disruptor;

import com.lmax.disruptor.EventFactory;
import org.springframework.stereotype.Service;

@Service
public class TradeEventFactory implements EventFactory<TradeEvent>{

    public TradeEvent newInstance() {
        return new TradeEvent();
    }
}

