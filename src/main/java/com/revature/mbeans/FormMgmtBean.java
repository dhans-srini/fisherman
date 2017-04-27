package com.revature.mbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sun.tracing.dtrace.ProviderAttributes;

@ManagedBean
@ViewScoped
public class FormMgmtBean {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PostConstruct
	public void init() {
		message = "sample dhans";
		System.out.println("inside init");
	}
}
