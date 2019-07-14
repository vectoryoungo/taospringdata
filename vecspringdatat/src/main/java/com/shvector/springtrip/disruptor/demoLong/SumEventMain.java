/**
 * @create 2019-07-14 10:36
 * @desc SumEventMain
 **/
package com.shvector.springtrip.disruptor.demoLong;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class SumEventMain {

    public static void main(String[] args) throws Exception {
        // 获取RungBuffer: ProducerType.MULTI： 多个生产者
        RingBuffer<SingleSumEvent> ringBuffer = RingBuffer.create(ProducerType.MULTI, SingleSumEvent::new, (int) (Math.pow(2, 20)), new YieldingWaitStrategy());

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        final CountDownLatch latch = new CountDownLatch(1);

        // 定义一个用于整合计算的消费者
        MergerSumEvent mergerSumEvent = new MergerSumEvent(latch);

        WorkerPool<SingleSumEvent> workerPool = new WorkerPool<SingleSumEvent>(ringBuffer, sequenceBarrier,
                new IntEventExceptionHandler(), mergerSumEvent);

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(Executors.newCachedThreadPool());

        final SumEventProducer p = new SumEventProducer(ringBuffer);
        Instant start = Instant.now();
        for(int i = 0 ; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        p.onData(100000000L);
                    }
                }
            }).start();
        }
        //等待所有任务完成
        latch.await();
        Instant end = Instant.now();
        System.out.println("500个一亿(500*100000000)总共花费的时间: "+ Duration.between(start,end).toMillis() + " 毫秒,值为:   " +mergerSumEvent.getSum());
        workerPool.halt(); //通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
    }




    static class IntEventExceptionHandler implements ExceptionHandler<Object> {
        public void handleEventException(Throwable ex, long sequence, Object event) {
        }

        public void handleOnStartException(Throwable ex) {
        }

        public void handleOnShutdownException(Throwable ex) {
        }
    }
}

