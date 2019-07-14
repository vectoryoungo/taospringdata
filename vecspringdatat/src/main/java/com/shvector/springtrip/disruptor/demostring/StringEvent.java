/**
 * @create 2019-07-14 09:59
 * @desc 用于传递数据的对象
 **/
package com.shvector.springtrip.disruptor.demostring;

public class StringEvent {

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringEvent [id=" + id + ", value=" + value + "]";
    }
}

