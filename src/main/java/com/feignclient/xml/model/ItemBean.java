package com.feignclient.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "Item")
public class ItemBean {
	String orgCode = "";
	@JsonProperty("ComplexQuery")
	ComplexQueryBean complexQueryBean;

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@XmlAttribute(name = "orgCode")
	public String getOrgCode() {
		return orgCode;
	}

	@XmlElement(name = "ComplexQuery")
	public ComplexQueryBean getComplexQueryBean() {
		if (complexQueryBean == null)
			complexQueryBean = new ComplexQueryBean();
		return complexQueryBean;
	}

	public void setComplexQueryBean(ComplexQueryBean complexQueryBean) {
		this.complexQueryBean = complexQueryBean;
	}

}