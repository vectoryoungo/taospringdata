/**
 * @create 2019-07-13 23:15
 * @desc ValueWrapper
 **/
package com.shvector.springtrip.disruptor.demoyunqi;

public abstract class ValueWrapper<T> {

    private T value;

    public ValueWrapper() {}

    public ValueWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

