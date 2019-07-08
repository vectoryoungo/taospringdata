/**
 * @create 2019-07-08 16:57
 * @desc EnumTxType
 **/
package com.shvector.springtrip.disruptor.demoi;

public enum EnumTxType {

    LIMIT_PRICE_TX_TYPE(1, "限价交易", "限价"),
    MARKET_PRICE_TX_TYPE(2, "市价交易", "市价");

    /**
     * 返回状态码
     */
    private int code;

    private String description;

    private String priceDes;

    EnumTxType(int code, String description, String priceDes) {
        this.code = code;
        this.description = description;
        this.priceDes = priceDes;
    }

    public static EnumTxType getByValues(int statusCode) {
        for (EnumTxType enumTxType : values()) {
            if (enumTxType.code == statusCode) {
                return enumTxType;
            }
        }

        return null;
    }
}

