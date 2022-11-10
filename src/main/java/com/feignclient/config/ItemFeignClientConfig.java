package com.feignclient.config;

import org.springframework.context.annotation.Bean;

import com.feignclient.config.decoders.Custom5xxErrorDecoder;
import com.feignclient.config.retry.CustomRetryer;

import feign.Logger;
import feign.codec.ErrorDecoder;

public class ItemFeignClientConfig extends FeignClientConfig {

	@Bean
	public ErrorDecoder itemErrorDecoder() {
		return new Custom5xxErrorDecoder();
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public CustomLogger customFeignRequestLogging() {
		return new CustomLogger();
	}
	
	@Bean
	public CustomRetryer retryer() {
		return new CustomRetryer();
	}

}
