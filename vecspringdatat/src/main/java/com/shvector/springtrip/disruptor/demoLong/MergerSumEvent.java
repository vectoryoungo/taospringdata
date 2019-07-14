/**
 * @create 2019-07-14 10:34
 * @desc MergerSumEvent
 **/
package com.shvector.springtrip.disruptor.demoLong;

import com.lmax.disruptor.WorkHandler;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MergerSumEvent implements WorkHandler<SingleSumEvent> {

        //数值太大Long型会溢出
        private volatile BigInteger sum = new BigInteger("0");

        //原子操作
        private volatile AtomicInteger count = new AtomicInteger(1);

        //闭锁
        private volatile CountDownLatch latch ;


	    public MergerSumEvent(CountDownLatch latch) {
            this.latch = latch;
        }

        //每个SingleSumEvent 线程计算出来的值交给MergerSumEvent来累加
        @Override
        public  void onEvent(SingleSumEvent singleSumEvent) throws Exception {
            sum = sum.add(new BigInteger(singleSumEvent.getValue()+""));
            System.out.println("线程"+singleSumEvent.getThread() +"  " + count + "个一亿求和的结果为:  " + sum );
            count.incrementAndGet();
            if(count.intValue() == 500) {
                latch.countDown();
            }
        }


        public BigInteger getSum() {
            return sum;
        }
}

