package com.feignclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feignclient.client.ItemFeignClient;
import com.feignclient.client.ShipnodeFeignClient;

@RestController
@RequestMapping("/feign")
public class CarznetController {
	
	@Autowired
	private ItemFeignClient itemFeignClient;
	@Autowired
	private ShipnodeFeignClient shipnodeFeignClient;

	@GetMapping("/faq/{name}")
	public String faq(@PathVariable String name) {
		String faq = itemFeignClient.faq(name);
		return faq;
	}
	
	@GetMapping("/faq1/{name}")
	public String faq1(@PathVariable String name) {
		String faq = shipnodeFeignClient.faq(name);
		return faq;
	}
}
