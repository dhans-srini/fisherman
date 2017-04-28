package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import com.revature.exception.BusinessException;
import com.revature.models.Form;

public class FormService {

  private static FormService instance;

  private FormService() {}

  public static FormService getInstance() {
    if (instance == null) {
      synchronized (FormService.class) {
        if (instance == null) {
          instance = new FormService();
        }
      }
    }
    return instance;

  }

  private final String insertTableSQL =
      "INSERT INTO form ( code,first_name, last_name, age,created_on) VALUES (?,?,?,?,?)";
  private final String formByCode =
      "select id,code,first_name, last_name, age,created_on from form f where f.code=?";

  public Form saveForm(Form form) throws BusinessException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = getConnection();
      PreparedStatement ps = con.prepareStatement(insertTableSQL);
      String formCode = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9);

      form.setFormCode(formCode);

      ps.setString(1, formCode);
      ps.setString(2, form.getFirstName());
      ps.setString(3, form.getLastName());
      ps.setString(4, form.getAge());
      ps.setObject(5, new Date());

      ps.executeUpdate();
    } catch (Exception e) {
      throw new BusinessException("Form save failed.", e);
    }
    return form;
  }

  public Form getForm(String formCode) throws BusinessException {
    Form form = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = getConnection();
      PreparedStatement ps = con.prepareStatement(formByCode);


      ps.setString(1, formCode);
      ResultSet rs = ps.executeQuery();
      form = new Form();
      while (rs.next()) {
        form.setId(rs.getLong("id"));
        form.setFormCode(rs.getString("code"));
        form.setFirstName(rs.getString("first_name"));
        form.setLastName(rs.getString("last_name"));
        form.setAge(rs.getString("age"));
        form.setCreatedOn(rs.getTimestamp("created_on"));
      }
      rs.close();
      ps.close();
      con.close();
    } catch (Exception e) {
      throw new BusinessException("Form get failed.", e);
    }
    return form;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/fisher_man?autoReconnect=true&zeroDateTimeBehavior=convertToNull",
        "root", "root");
  }



}
