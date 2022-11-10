package com.feignclient.xml.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrBean {
	@JsonProperty("And")
	List<AndBean> andBeanList;

	public void setAndBeanList(List<AndBean> andBeanList) {
		this.andBeanList = andBeanList;
	}

	@XmlElement(name = "And")
	public List<AndBean> getAndBeanList() {
		if (andBeanList == null)
			andBeanList = new ArrayList<AndBean>();
		return andBeanList;
	}

}