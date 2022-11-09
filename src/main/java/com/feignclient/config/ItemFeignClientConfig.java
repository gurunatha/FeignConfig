package com.feignclient.config;

import org.springframework.context.annotation.Bean;

import com.feignclient.config.decoders.Custom5xxErrorDecoder;

import feign.codec.ErrorDecoder;

public class ItemFeignClientConfig extends FeignClientConfig{

	@Bean
	public ErrorDecoder itemErrorDecoder() {
		return new Custom5xxErrorDecoder();
	}
}
