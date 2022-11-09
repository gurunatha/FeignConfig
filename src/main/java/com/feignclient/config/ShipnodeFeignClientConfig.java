package com.feignclient.config;

import org.springframework.context.annotation.Bean;

import com.feignclient.config.decoders.ShipnodeErrorDecoder;

import feign.codec.ErrorDecoder;

public class ShipnodeFeignClientConfig extends FeignClientConfig{

	@Bean
	public ErrorDecoder shipnodeErrorDecoder() {
		return new ShipnodeErrorDecoder();
	}
}
