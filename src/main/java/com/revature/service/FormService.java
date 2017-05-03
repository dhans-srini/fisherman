package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.revature.exception.BusinessException;
import com.revature.models.Form;
import com.revature.models.FormReviewHistory;

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

  private final static String insertTableSQL =
      "INSERT INTO form (name, fathers_name, is_married,section,created_on,status,code,address,biometric_id,"
          + "ration_card_id,voter_id,adhaar_no,national_bank_details,group_acc_dtls,coop_bank_dtl,"
          + "tn_fish_association_dtl,work_type,is_prev_year_benefitter,benefitted_year,is_benefitter,"
          + "is_getting_relief_fund) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  private final static String insertFormHistorySQL = "INSERT INTO form_review_history "
      + "( comments,status, reviewed_by, form,reviewed_on) VALUES (?,?,?,?,?)";
  private final static String updateTableSQL =
      "update form set name=?,fathers_name=?,is_married=?,section=?,updated_on=?,status=?,"
          + "code=?,address=?,biometric_id=?,ration_card_id=?,voter_id=?,adhaar_no=?,"
          + "national_bank_details=?,group_acc_dtls=?,coop_bank_dtl=?,tn_fish_association_dtl=?,"
          + "work_type=?,is_prev_year_benefitter=?,benefitted_year=?,is_benefitter=?,is_getting_relief_fund=?"
          + " where id=?";
  private final static String formByCode =
      "select id,code,name, fathers_name, is_married,section,created_on,status,address,biometric_id,"
          + "ration_card_id,voter_id,adhaar_no,national_bank_details,group_acc_dtls,coop_bank_dtl,"
          + "tn_fish_association_dtl,work_type,is_prev_year_benefitter,benefitted_year,is_benefitter,"
          + "is_getting_relief_fund from form f where f.code=?";
  private final static String formReviewHistoryByCode = "select his.id,his.status, his.comments "
      + "from form f join form_review_history his on his.form=f.id where f.code=?";
  private final static String updateFormStatusSQL = "update form set status=? where id=?";

  public Form saveForm(Form form) throws BusinessException {
    try {
      Connection con = getConnection();
      form.setStatus("submitted");
      if (form.getId() == null) {
        PreparedStatement ps = con.prepareStatement(insertTableSQL);
        String formCode = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9);

        form.setFormCode(formCode);
        ps.setString(1, form.getName());
        ps.setString(2, form.getFathersName());
        ps.setBoolean(3, form.getMarried());
        ps.setString(4, form.getSection());
        ps.setObject(5, new Date());
        ps.setObject(6, form.getStatus());
        ps.setObject(7, form.getFormCode());
        ps.setObject(8, form.getAddress());
        ps.setString(9, form.getBiometricId());
        ps.setString(10, form.getRationCardId());
        ps.setString(11, form.getVoterId());
        ps.setString(12, form.getAdhaarNo());
        ps.setString(13, form.getNationalBankDetails());
        ps.setString(14, form.getGroupAccDetails());
        ps.setString(15, form.getCooperativeBankDetails());
        ps.setString(16, form.getTnFishAssociationDetails());
        ps.setString(17, form.getWorkType());
        ps.setBoolean(18, form.getIsPrevYearbenefitter());
        ps.setString(19, form.getBenefittedYear());
        ps.setBoolean(20, form.getIsBenefitter());
        ps.setBoolean(21, form.getIsGettingReliefFund());
        ps.executeUpdate();
      } else {
        PreparedStatement ps = con.prepareStatement(updateTableSQL);
        ps.setString(1, form.getName());
        ps.setString(2, form.getFathersName());
        ps.setBoolean(3, form.getMarried());
        ps.setString(4, form.getSection());
        ps.setObject(5, new Date());
        ps.setObject(6, form.getStatus());
        ps.setObject(7, form.getFormCode());
        ps.setObject(8, form.getAddress());
        ps.setString(9, form.getBiometricId());
        ps.setString(10, form.getRationCardId());
        ps.setString(11, form.getVoterId());
        ps.setString(12, form.getAdhaarNo());
        ps.setString(13, form.getNationalBankDetails());
        ps.setString(14, form.getGroupAccDetails());
        ps.setString(15, form.getCooperativeBankDetails());
        ps.setString(16, form.getTnFishAssociationDetails());
        ps.setString(17, form.getWorkType());
        ps.setBoolean(18, form.getIsPrevYearbenefitter());
        ps.setString(19, form.getBenefittedYear());
        ps.setBoolean(20, form.getIsBenefitter());
        ps.setBoolean(21, form.getIsGettingReliefFund());
        ps.setObject(22, form.getId());
        ps.executeUpdate();
      }

    } catch (Exception e) {
      throw new BusinessException("Form save failed.", e);
    }
    return form;
  }

  public Form getForm(String formCode) throws BusinessException {
    Form form = null;
    try {
      Connection con = getConnection();
      PreparedStatement ps = con.prepareStatement(formByCode);

      ps.setString(1, formCode);
      ResultSet rs = ps.executeQuery();

      int rowcount = 0;
      if (rs.last()) {
        rowcount = rs.getRow();
        rs.beforeFirst();
      }
      if (rowcount != 0) {
        form = new Form();
      }

      while (rs.next()) {
        form.setId(rs.getLong("id"));
        form.setFormCode(rs.getString("code"));
        form.setName(rs.getString("name"));
        form.setFathersName(rs.getString("fathers_name"));
        form.setMarried(rs.getBoolean("is_married"));
        form.setSection(rs.getString("section"));
        form.setCreatedOn(rs.getTimestamp("created_on"));
        form.setStatus(rs.getString("status"));
        form.setAddress(rs.getString("address"));
        form.setAdhaarNo(rs.getString("adhaar_no"));
        form.setBiometricId(rs.getString("biometric_id"));
        form.setRationCardId(rs.getString("ration_card_id"));
        form.setVoterId(rs.getString("voter_id"));
        form.setNationalBankDetails(rs.getString("national_bank_details"));
        form.setGroupAccDetails(rs.getString("group_acc_dtls"));
        form.setCooperativeBankDetails(rs.getString("coop_bank_dtl"));
        form.setTnFishAssociationDetails(rs.getString("tn_fish_association_dtl"));
        form.setWorkType(rs.getString("work_type"));
        form.setIsPrevYearbenefitter(rs.getBoolean("is_prev_year_benefitter"));
        form.setBenefittedYear(rs.getString("benefitted_year"));
        form.setIsBenefitter(rs.getBoolean("is_benefitter"));
        form.setIsGettingReliefFund(rs.getBoolean("is_getting_relief_fund"));
      }
      rs.close();
      ps.close();
      con.close();
    } catch (Exception e) {
      throw new BusinessException("Form get failed.", e);
    }
    return form;
  }

  private Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    return DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/fisher_man?autoReconnect=true&zeroDateTimeBehavior=convertToNull",
        "root", "pass123$");
  }

  public List<FormReviewHistory> getFormReviewHistory(String formCode) throws BusinessException {
    List<FormReviewHistory> formReviewHistories = new ArrayList<>();
    try {
      Connection con = getConnection();
      PreparedStatement ps = con.prepareStatement(formReviewHistoryByCode);

      ps.setString(1, formCode);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        FormReviewHistory history = new FormReviewHistory();
        history.setId(rs.getLong("id"));
        history.setComments(rs.getString("comments"));
        history.setStatus(rs.getString("status"));
        formReviewHistories.add(history);
      }
      rs.close();
      ps.close();
      con.close();
    } catch (Exception e) {
      throw new BusinessException("Form History get failed.", e);
    }
    return formReviewHistories;
  }

  public FormReviewHistory saveReviewHistory(FormReviewHistory his) throws BusinessException {
    try {
      Connection con = getConnection();

      PreparedStatement ps = con.prepareStatement(insertFormHistorySQL);
      ps.setString(1, his.getComments());
      ps.setString(2, his.getStatus());
      ps.setLong(3, his.getReviewedBy());
      ps.setLong(4, his.getFormId());
      ps.setObject(5, new Date());
      ps.executeUpdate();

      ps = con.prepareStatement(updateFormStatusSQL);
      ps.setString(1, his.getStatus());
      ps.setLong(2, his.getFormId());
      ps.executeUpdate();

    } catch (Exception e) {
      throw new BusinessException("Form save failed.", e);
    }
    return his;
  }

}
