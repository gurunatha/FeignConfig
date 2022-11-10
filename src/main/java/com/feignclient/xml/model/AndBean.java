package com.feignclient.xml.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AndBean {
	OrBean orBean;

	@XmlElement(name = "Or")
	public OrBean getOrBean() {
		return orBean;
	}

	public void setOrBean(OrBean orBean) {
		this.orBean = orBean;
	}

	@JsonProperty("Exp")
	List<ExpBean> expBeanList;

	public void setExpBeanList(List<ExpBean> expBeanList) {
		this.expBeanList = expBeanList;
	}

	@XmlElement(name = "Exp")
	public List<ExpBean> getExpBeanList() {
		if (expBeanList == null)
			expBeanList = new ArrayList<ExpBean>();
		return expBeanList;
	}

}