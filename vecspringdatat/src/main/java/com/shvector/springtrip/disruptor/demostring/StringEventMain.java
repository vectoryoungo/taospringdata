/**
 * @create 2019-07-14 10:29
 * @desc this is test main function
 **/
package com.shvector.springtrip.disruptor.demostring;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringEventMain {

    public static void main(String[] args) throws Exception {
        //创建一个线程池
        ExecutorService executorPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //创建Event工厂
        StringEventFactory factory = new StringEventFactory();
		/*
		 *	  创建Disruptor对象
		 *   eventFactory, 传入实现了EventFactory借口的工厂类
		 *   ringBufferSize, 用来存储数据的， 值为 2^n
		 *   executor, 线程池
		 *   producerType, 类型，可以是多个生产者，也可以是单个生产者
		 *   waitStrategy, 使用什么策略，消费者如何等待生产者放入disruptor中 :
				     BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
					 SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
					 YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
		 */
        Disruptor<StringEvent> disruptor = new Disruptor<>(factory, (int)Math.pow(2, 20), executorPool, ProducerType.SINGLE, new YieldingWaitStrategy());
        //关联处理器，也就是消费者，连接消费事件方法
        disruptor.handleEventsWith(new StringEventHandler());
        //启动
        disruptor.start();
        //获取RingBuffer,模拟生产者发布消息
        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();

        StringEventProducerWithTranslator producer = new StringEventProducerWithTranslator(ringBuffer);
        //StringEventProducer producer = new StringEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //闭锁控制线程同步
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 10 ; i ++) {
                    //下面是进行触发事件并且发布
                    byteBuffer.put(new String("生产者"+ i +"发布消息").getBytes());
                    producer.sendData(byteBuffer);
                    //模拟进行其他操作的耗时
                    /*try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
                countDownLatch.countDown();
            }
        },"Thread2").start();;
        //等待
        countDownLatch.await();
        disruptor.shutdown(); //关闭 disruptor
        executorPool.shutdown(); //关闭线程池
    }
}

