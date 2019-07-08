/**
 * @create 2019-07-08 14:24
 * @desc translator
 **/
package com.shvector.springtrip.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.shvector.springtrip.dto.TradeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class Translator implements EventTranslatorOneArg<TradeEvent,TradeDTO>{

    public void translateTo(TradeEvent event, long sequence, TradeDTO arg0) {
        event.setAmount(arg0.getAmount());
        event.setBusinessType(arg0.getBusinessType());
        event.setOrderNo(arg0.getOrderNo());
        event.setPrice(arg0.getPrice());
        event.setRelationId(arg0.getRelationId());
        event.setTxType(arg0.getTxType());
        event.setUserId(arg0.getUserId());
        //BeanUtils.copyProperties(arg0,event);
    }
}

