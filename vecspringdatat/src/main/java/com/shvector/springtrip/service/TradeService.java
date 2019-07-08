/**
 * @create 2019-07-08 11:39
 * @desc test wrap trade service
 **/
package com.shvector.springtrip.service;

import com.shvector.springtrip.dto.TradeDTO;

public interface TradeService {

    void order(TradeDTO tradeDTO);
}

