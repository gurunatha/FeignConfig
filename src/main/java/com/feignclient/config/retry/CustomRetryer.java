package com.feignclient.config.retry;


import org.springframework.stereotype.Component;

import feign.RetryableException;
import feign.Retryer;

@Component
public class CustomRetryer implements Retryer {

    private int retryMaxAttempt;

    private long retryInterval;

    private int attempt = 1;
    
    public CustomRetryer() {
    	
    }

    public CustomRetryer(int retryMaxAttempt, Long retryInterval) {
        this.retryMaxAttempt = retryMaxAttempt;
        this.retryInterval = retryInterval;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
       System.out.println("Feign retry attempt "+attempt+" due to {} "+ e.getMessage());

        if(attempt++ == retryMaxAttempt){
            throw e;
        }
        try {
            Thread.sleep(retryInterval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }

    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(6, 2000L);
    }
}