/**
 * @create 2019-07-08 15:12
 * @desc test trade event
 **/
package com.shvector.springtrip.disruptor.demoi;

import java.util.List;
import java.util.Queue;

public class MyTradeEvent {

    private Queue<MyInnerData> seller;
    private Queue<MyInnerData> buyer;

    public Queue<MyInnerData> getSeller() {
        return seller;
    }

    public void setSeller(Queue<MyInnerData> seller) {
        this.seller = seller;
    }

    public Queue<MyInnerData> getBuyer() {
        return buyer;
    }

    public void setBuyer(Queue<MyInnerData> buyer) {
        this.buyer = buyer;
    }

    /*private List<MyInnerData> buyer;
    private List<MyInnerData> seller;

    public List<MyInnerData> getBuyer() {
        return buyer;
    }

    public void setBuyer(List<MyInnerData> buyer) {
        this.buyer = buyer;
    }

    public List<MyInnerData> getSeller() {
        return seller;
    }

    public void setSeller(List<MyInnerData> seller) {
        this.seller = seller;
    }*/
}

