/**
 * @create 2019-07-14 10:25
 * @desc StringEventFactory 实现Disruptor提供的工厂接口，工厂方法模式
 **/
package com.shvector.springtrip.disruptor.demostring;

import com.lmax.disruptor.EventFactory;

public class StringEventFactory implements EventFactory<StringEvent> {

    @Override
    public StringEvent newInstance() {
        //我们自定义的对象
        return new StringEvent();
    }

}

