/**
 * @create 2019-07-13 23:26
 * @desc SeriesDataEventHandler
 **/
package com.shvector.springtrip.disruptor.demoyunqi;

import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class SeriesDataEventHandler implements WorkHandler<SeriesDataEvent> {

    private Logger logger = LoggerFactory.getLogger(SeriesDataEventHandler.class);
    @Override
    public void onEvent(SeriesDataEvent event) {
        if (event.getValue() == null || StringUtils.isEmpty(event.getValue().getDeviceInfoStr())) {
            logger.warn("receiver series data is empty!");
        }
        logger.error("hello word!");
    }

}

