/**
 * @create 2019-07-08 15:12
 * @desc test trade event
 **/
package com.shvector.springtrip.disruptor.demoi;

import java.util.List;

public class MyTradeEvent {

    private List<MyInnerData> buyer;
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
    }
}

