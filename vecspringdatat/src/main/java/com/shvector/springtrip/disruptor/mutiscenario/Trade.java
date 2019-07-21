/**
 * @create 2019-07-14 09:26
 * @desc Trade
 **/
package com.shvector.springtrip.disruptor.mutiscenario;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Trade {
    private String      orderNo;
    private Integer     relationId;         // 货币交易对
    private Long        userId;             // 用户id
    private BigDecimal  price;              // 价格：from req
    private BigDecimal  amount;             // 数量：from req
    private Integer     businessType;       // 买卖类型：买-1/卖-2
    private Integer     txType;             // 交易类型：市价交易-1/限价交易-2
    private static PriorityBlockingQueue<Trade> buyer = new PriorityBlockingQueue<Trade>(1024 * 512, new Comparator<Trade>() {
        @Override
        public int compare(Trade o1, Trade o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }
    });
    private static PriorityBlockingQueue<Trade> seller = new PriorityBlockingQueue<>(1024 * 512, new Comparator<Trade>() {
        @Override
        public int compare(Trade o1, Trade o2) {
            return -o1.getPrice().compareTo(o2.getPrice());
        }
    });             //卖单队列
    private AtomicInteger count = new AtomicInteger(0);

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getTxType() {
        return txType;
    }

    public void setTxType(Integer txType) {
        this.txType = txType;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    public PriorityBlockingQueue<Trade> getBuyer() {
        return buyer;
    }

    public void setBuyer(PriorityBlockingQueue<Trade> buyer) {
        this.buyer = buyer;
    }

    public PriorityBlockingQueue<Trade> getSeller() {
        return seller;
    }

    public void setSeller(PriorityBlockingQueue<Trade> seller) {
        this.seller = seller;
    }
}

