/**
 * @create 2019-07-14 10:26
 * @desc 事件的处理器，也就是消费者，这里模拟的是将数据打印处理
 **/
package com.shvector.springtrip.disruptor.demostring;

import com.lmax.disruptor.EventHandler;

public class StringEventHandler implements EventHandler<StringEvent> {

    @Override
    public void onEvent(StringEvent stringEvent, long sequence, boolean bool) throws Exception {
        System.out.println("StringEventHandler(消费者):  "  + stringEvent +", sequence= "+sequence+",bool="+bool);
    }
}

