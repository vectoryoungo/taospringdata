/**
 * @create 2019-07-14 11:04
 * @desc LongEventTranslator
 **/
package com.shvector.springtrip.disruptor.demoLong;

import com.lmax.disruptor.EventTranslatorOneArg;

public class LongEventTranslator  implements EventTranslatorOneArg<LongEvent, Long> {
    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        event.setNumber(arg0);
    }
}

