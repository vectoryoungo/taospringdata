/**
 * @create 2019-07-14 10:28
 * @desc 一个Translator可以看做一个事件初始化器
 **/
package com.shvector.springtrip.disruptor.demostring;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class StringEventProducerWithTranslator {

    private final RingBuffer<StringEvent> ringBuffer;

    //填充数据
    public static final EventTranslatorOneArg<StringEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<StringEvent, ByteBuffer>() {
        @Override
        public void translateTo(StringEvent stringEvent, long sequence, ByteBuffer byteBuffer) {
            // 从byteBuffer中读取传过来的值
            byteBuffer.flip();
            byte[] dst = new byte[byteBuffer.limit()];
            byteBuffer.get(dst, 0, dst.length);
            byteBuffer.clear();
            // 为stringEvent赋值，填充数据
            stringEvent.setValue(new String(dst));
            stringEvent.setId((int) sequence);
        }
    };

    public StringEventProducerWithTranslator(RingBuffer<StringEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    //发布事件
    public void sendData(ByteBuffer byteBuffer) {
        ringBuffer.publishEvent(TRANSLATOR, byteBuffer);
    }
}

