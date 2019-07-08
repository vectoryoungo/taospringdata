/**
 * @create 2019-07-08 14:09
 * @desc process event hanlder
 **/
package com.shvector.springtrip.disruptor;

import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Service;

@Service
public class TradeEventHandler implements EventHandler<TradeEvent>{

    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        //撮合

    }
}

