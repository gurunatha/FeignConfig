package com.feignclient.xml.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComplexQueryBean {
	String operator = "";
	@JsonProperty("And")
	AndBean andBean;

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@XmlAttribute(name = "operator")
	public String getOperator() {
		return operator;
	}

	@XmlElement(name = "And")
	public AndBean getAndBean() {
		if (andBean == null)
			andBean = new AndBean();
		return andBean;
	}

	public void setAndBean(AndBean andBean) {
		this.andBean = andBean;
	}

}