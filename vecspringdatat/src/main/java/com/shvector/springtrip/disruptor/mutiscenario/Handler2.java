/**
 * @create 2019-07-14 09:34
 * @desc Handler2
 **/
package com.shvector.springtrip.disruptor.mutiscenario;

import com.lmax.disruptor.EventHandler;

import java.math.BigDecimal;
import java.util.concurrent.PriorityBlockingQueue;

public class Handler2 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence,  boolean endOfBatch) throws Exception {
        //System.out.println("handler2: set price");
        //event.setPrice(new BigDecimal("17.0"));
        //Thread.sleep(1000);
        //System.out.println(" seller " + event.getSeller().size());
        //System.out.println(" buyer " + event.getBuyer().size());

        if (event.getSeller() != null && event.getBuyer() != null) {

            if (event.getBuyer().element() != null && event.getSeller().element() != null) {

                Trade seller = event.getSeller().element();
                Trade buyer = event.getBuyer().element();
                System.out.println(" seller price " + seller.getPrice());
                System.out.println(" buyer price " + buyer.getPrice());
                PriorityBlockingQueue<Trade> sellerQueue = seller.getSeller();
                PriorityBlockingQueue<Trade> buyerQueue = buyer.getBuyer();
                BigDecimal buyPrice = buyer.getPrice();
                BigDecimal sellPrice = seller.getPrice();
                BigDecimal buyAmount = buyer.getAmount();
                BigDecimal sellAmount = seller.getAmount();

                if (buyAmount.compareTo(BigDecimal.ZERO) ==0){
                    System.out.println("buy bad operation");
                    return;
                }

                if (sellAmount.compareTo(BigDecimal.ZERO) ==0){
                    System.out.println("sell bad operation");
                    return;
                }
                //limit price
                if (buyPrice.compareTo(sellPrice) > 0) {
                    //all buy
                    if (buyAmount.compareTo(sellAmount) > 0) {
                        System.out.println("¥¥¥¥¥¥¥¥¥");
                        System.out.println(" deal ");
                        System.out.println(event.getOrderNo());
                        System.out.println("buy amount");
                        System.out.println(buyAmount);
                        System.out.println("sell amount ");
                        System.out.println(sellAmount);
                        sellerQueue.take();//remove the match
                        System.out.println("buy not buy enough need more ");
                        System.out.println(buyAmount.subtract(sellAmount));
                        System.out.println("¥¥¥¥¥¥¥¥¥");
                    }else if (buyAmount.compareTo(sellAmount) < 0){
                        //buy part of goods regenerate goods
                        System.out.println("$$$$$$$$$$");
                        System.out.println(" deal ");
                        System.out.println(event.getOrderNo());
                        System.out.println("buy amount");
                        System.out.println(buyAmount);
                        System.out.println("sell amount ");
                        System.out.println(sellAmount);
                        System.out.println("sell part of goods regenerate goods need sell remain " + (sellAmount.subtract(buyAmount)));
                        System.out.println("$$$$$$$$$$");
                    }else {
                        // just match perfect buy 1 sell 1
                        System.out.println("**************");
                        System.out.println(" deal ");
                        System.out.println(event.getOrderNo());
                        System.out.println("buy amount");
                        System.out.println(buyAmount);
                        System.out.println("sell amount ");
                        System.out.println(sellAmount);
                        System.out.println("perfect match");
                        System.out.println("**************");
                    }
                }
            }
        }
    }
}

