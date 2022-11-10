package com.feignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.feignclient.config.ItemFeignClientConfig;


@FeignClient(name="item",url="localhost:8081/ims/",configuration = ItemFeignClientConfig.class)
public interface ItemFeignClient {

	@GetMapping(value = "item/{name}")
	public String faq(@PathVariable String name);
	
	@PostMapping(value="itemlist",consumes = "application/xml",produces = "application/json")
	public String itemList(@RequestBody String request);
}
