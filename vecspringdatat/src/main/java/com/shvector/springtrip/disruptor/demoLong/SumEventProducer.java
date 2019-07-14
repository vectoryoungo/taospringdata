/**
 * @create 2019-07-14 10:35
 * @desc SumEventProducer
 **/
package com.shvector.springtrip.disruptor.demoLong;

import com.lmax.disruptor.RingBuffer;

public class SumEventProducer {

    private final RingBuffer<SingleSumEvent> ringBuffer;

    public SumEventProducer(RingBuffer<SingleSumEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(long sum) {
        long sequence = ringBuffer.next();
        try {
            SingleSumEvent singleSumEvent = ringBuffer.get(sequence);
            singleSumEvent.calculate(1, sum);
            singleSumEvent.setValue(singleSumEvent.getValue());
            singleSumEvent.setThread(Thread.currentThread().getName());
        } finally {
            //发布事件
            ringBuffer.publish(sequence);
        }
    }
}

