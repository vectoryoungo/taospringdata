/**
 * @create 2019-07-08 15:17
 * @desc data object
 **/
package com.shvector.springtrip.disruptor.demoi;

import java.util.List;

public class MyData {

    private List<MyInnerData> seller;
    private List<MyInnerData> buyer;

    public List<MyInnerData> getSeller() {
        return seller;
    }

    public void setSeller(List<MyInnerData> seller) {
        this.seller = seller;
    }

    public List<MyInnerData> getBuyer() {
        return buyer;
    }

    public void setBuyer(List<MyInnerData> buyer) {
        this.buyer = buyer;
    }
}

