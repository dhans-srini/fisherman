package com.revature.mbeans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.revature.models.Form;
import com.revature.models.User;
import com.revature.service.AdminService;
import com.revature.utils.Utils;

@ManagedBean
@ViewScoped
public class AdminMgmtBean {
  private AdminService adminService = AdminService.getInstance();
  private String userName;
  private String password;

  private List<Form> forms;
  private Boolean isValidUser;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Form> getForms() {
    return forms;
  }

  public void setForms(List<Form> forms) {
    this.forms = forms;
  }

  public Boolean getIsValidUser() {
    return isValidUser;
  }

  public void setIsValidUser(Boolean isValidUser) {
    this.isValidUser = isValidUser;
  }

  @PostConstruct
  public void init() {
    if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
        .get("user") != null) {
      loadForms();
    }
  }

  public void validateUser() {
    try {
      User user = adminService.validateUser(userName, password);
      if (user != null) {
        isValidUser = true;
      }
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
      loadForms();
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }

  }

  public void loadForms() {
    try {
      this.forms = adminService.getAllForms();
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void logout() {
    try {
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
      FacesContext.getCurrentInstance().getExternalContext()
          .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
              + "/home.xhtml");
    } catch (IOException e) {
    }
  }

  public void download() throws IOException {
    try {
      if (CollectionUtils.isNotEmpty(forms)) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset();
        ec.setResponseHeader("Content-Disposition",
            "attachment; filename=\"" + "fisherman_report" + new Date() + ".xlsx\"");

        createExcel(ec.getResponseOutputStream());
        fc.responseComplete();
      }
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }
  }

  private void createExcel(OutputStream out) {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet("Fisher man data");
      // This data needs to be written (Object[])
      Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
      data.put(1, new Object[] {"S.No", "Code", "Name", "Father's Name", "Biometric id", "status"});
      for (int i = 0; i < forms.size(); i++) {
        Form frm = forms.get(i);
        data.put(i + 2, new Object[] {i + 1, frm.getFormCode(), frm.getName(), frm.getFathersName(),
            frm.getBiometricId(), frm.getStatus()});
      }


      Set<Integer> keyset = data.keySet();
      int rownum = 0;
      for (Integer key : keyset) {
        Row row = sheet.createRow(rownum++);
        Object[] objArr = data.get(key);
        int cellnum = 0;
        for (Object obj : objArr) {
          Cell cell = row.createCell(cellnum++);
          if (obj instanceof String)
            cell.setCellValue((String) obj);
          else if (obj instanceof Integer)
            cell.setCellValue((Integer) obj);
        }
      }
      sheet.autoSizeColumn(1);
      sheet.autoSizeColumn(2);
      sheet.autoSizeColumn(3);
      workbook.write(out);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
