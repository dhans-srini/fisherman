package com.revature.models;

import java.util.Date;

public class FormAttachment {

  private Long id;
  private Long formId;
  private String uniqueName;
  private String fileType;
  private String fileName;
  private Date uploadedOn;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFormId() {
    return formId;
  }

  public void setFormId(Long formId) {
    this.formId = formId;
  }

  public String getUniqueName() {
    return uniqueName;
  }

  public void setUniqueName(String uniqueName) {
    this.uniqueName = uniqueName;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Date getUploadedOn() {
    return uploadedOn;
  }

  public void setUploadedOn(Date uploadedOn) {
    this.uploadedOn = uploadedOn;
  }

}
