/**
 * @create 2019-07-08 11:18
 * @desc test disruptor trade efficency
 **/
package com.shvector.springtrip.disruptor;

import java.math.BigDecimal;

public class TradeEvent {

    private String      orderNo;
    private Integer     relationId;         // 货币交易对
    private Long        userId;             // 用户id
    private BigDecimal  price;              // 价格：from req
    private BigDecimal  amount;             // 数量：from req
    private Integer     businessType;       // 买卖类型：买-1/卖-2
    private Integer     txType;             // 交易类型：市价交易-1/限价交易-2

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
}

