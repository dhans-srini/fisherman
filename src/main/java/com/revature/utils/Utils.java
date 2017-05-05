package com.revature.utils;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utils {

	public static FacesMessage addErrorMessage(String message) {
		FacesMessage result = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "");
		FacesContext.getCurrentInstance().addMessage(null, result);
		return result;
	}

	public static FacesMessage addInfoMessage(String message) {
		FacesMessage result = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, result);
		return result;
	}

	public static String getValueFromPageLabels(String key) {
		String val = key;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("pageLabels");
			val = bundle.getString(key);
		} catch (Exception e) {
			System.out.println("key not found");
		}
		return val;
	}

}
