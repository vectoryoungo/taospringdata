/**
 * @create 2019-07-14 10:34
 * @desc SingleSumEvent
 **/
package com.shvector.springtrip.disruptor.demoLong;

public class SingleSumEvent {

    private String thread;

    private long value;

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }


    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }


    public void calculate(long start , long end) {
        for(long i = start ; i <= end ; i++) {
            value += i;
        }
    }

    @Override
    public String toString() {
        return "SingleSumEvent [thread=" + thread + ", value=" + value + "]";
    }
}

