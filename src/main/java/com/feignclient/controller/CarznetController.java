package com.feignclient.controller;

import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feignclient.client.ItemFeignClient;
import com.feignclient.client.ShipnodeFeignClient;
import com.feignclient.xml.model.AndBean;
import com.feignclient.xml.model.ComplexQueryBean;
import com.feignclient.xml.model.ExpBean;
import com.feignclient.xml.model.ItemBean;
import com.feignclient.xml.model.OrBean;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/feign")
@Slf4j
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
	@GetMapping("/item")
	public String item() {
		
		ExpBean bean = new ExpBean();
		bean.setName("ItemId");
		bean.setValue("123456789");
		ExpBean bean1 = new ExpBean();
		bean1.setName("UnitOfMeasure");
		bean1.setValue("EACH");
		
		ExpBean bean2 = new ExpBean();
		bean2.setName("ItemId");
		bean2.setValue("123456789");
		ExpBean bean21 = new ExpBean();
		bean21.setName("UnitOfMeasure");
		bean21.setValue("EACH");
		
		AndBean andBean = new AndBean();
		andBean.setExpBeanList(Arrays.asList(bean,bean1));
		AndBean andBean1 = new AndBean();
		andBean1
		.setExpBeanList(Arrays.asList(bean2,bean21));
		
		AndBean andBean2 = new AndBean();
		
		OrBean orBean = new OrBean();
		orBean.setAndBeanList(Arrays.asList(andBean,andBean1));
		andBean2.setOrBean(orBean);
		
		ComplexQueryBean complexQueryBean = new ComplexQueryBean();
		complexQueryBean.setAndBean(andBean2);
		complexQueryBean.setOperator("EACH");
		
		ItemBean itemBean = new ItemBean();
		itemBean.setOrgCode("WM_BU");
		itemBean.setComplexQueryBean(complexQueryBean);
		
		var sw = new StringWriter();
		JAXB.marshal(itemBean, sw);
		String xml = sw.toString();
		String itemList = itemFeignClient.itemList(xml);
		return itemList;
	}
}
