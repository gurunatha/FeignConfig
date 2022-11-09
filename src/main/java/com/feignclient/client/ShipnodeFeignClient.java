package com.feignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.feignclient.config.ShipnodeFeignClientConfig;


@FeignClient(name="shipnode",url="localhost:8081/ims/",configuration = ShipnodeFeignClientConfig.class)
public interface ShipnodeFeignClient {

	@GetMapping(value = "shipnode/{name}")
	public String faq(@PathVariable String name);
}
