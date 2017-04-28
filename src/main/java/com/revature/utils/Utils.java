package com.revature.utils;

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

}
