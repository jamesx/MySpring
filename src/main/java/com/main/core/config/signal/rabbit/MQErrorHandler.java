package com.main.core.config.signal.rabbit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ErrorHandler;

public class MQErrorHandler implements ErrorHandler {
	
	protected Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void handleError(Throwable cause) {
    	logger.error("An error occurred.", cause);
    }
    
}
