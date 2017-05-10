package com.revature.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
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

  public static String getValueFromAppProperties(String key) {
    String val = key;
    try {
      ResourceBundle bundle = ResourceBundle.getBundle("application");
      val = bundle.getString(key);
    } catch (Exception e) {
      System.out.println("key not found");
    }
    return val;
  }

  public static Connection getConnection() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    String url = getValueFromAppProperties("dburl");
    String userName = getValueFromAppProperties("dbusername");
    String pwd = getValueFromAppProperties("dbpassword");
    return DriverManager.getConnection(url, userName, pwd);
  }

  public static String getEncryptedString(String input)
      throws NoSuchAlgorithmException, UnsupportedEncodingException {
    byte[] bytesOfMsg = (input).getBytes("UTF-8");
    MessageDigest md = null;
    md = MessageDigest.getInstance("MD5");
    byte[] theDigest = md.digest(bytesOfMsg);
    StringBuilder sb = new StringBuilder(2 * theDigest.length);
    for (byte b : theDigest) {
      sb.append(String.format("%02x", b & 0xff));
    }
    return sb.toString();
  }

}
