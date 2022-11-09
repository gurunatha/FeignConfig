package com.feignclient.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;

import com.feignclient.config.decoders.ItemErrorDecoder;

//@Configuration
public class FeignClientConfig {

	@Bean
	public CloseableHttpClient httpClient() {
		return HttpClients.custom().setMaxConnPerRoute(200).setMaxConnTotal(200).build();
	}

}
