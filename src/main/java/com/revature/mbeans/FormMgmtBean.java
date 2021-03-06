package com.revature.mbeans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.apache.commons.collections4.CollectionUtils;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.FontSettings;
import com.aspose.words.SaveFormat;
import com.aspose.words.Shape;
import com.revature.exception.BusinessException;
import com.revature.models.Form;
import com.revature.models.FormAttachment;
import com.revature.models.FormReviewHistory;
import com.revature.models.Socity;
import com.revature.models.User;
import com.revature.models.Village;
import com.revature.service.FormService;
import com.revature.utils.Utils;

@ManagedBean
@ViewScoped
public class FormMgmtBean {

  private FormService formService = FormService.getInstance();
  private Form form;
  private String formCode;

  private String pageFlag;
  private String adminPage;
  private List<FormReviewHistory> formReviewHistory;

  private String reviewComments;

  private String pageScript;

  private List<Village> villages;
  private List<Socity> socities;
  private List<Socity> socitiesBasedOnVillages;

  private Part file;
  private String fileUploadFlag;
  private String fileType;

  private String photoUniqueName;

  List<FormAttachment> formAttachemts;

  public FormService getFormService() {
    return formService;
  }

  public void setFormService(FormService formService) {
    this.formService = formService;
  }

  public Form getForm() {
    return form;
  }

  public void setForm(Form form) {
    this.form = form;
  }

  public String getFormCode() {
    return formCode;
  }

  public void setFormCode(String formCode) {
    this.formCode = formCode;
  }

  public String getPageFlag() {
    return pageFlag;
  }

  public void setPageFlag(String pageFlag) {
    pageScript = null;
    this.pageFlag = pageFlag;
  }

  public String getAdminPage() {
    return adminPage;
  }

  public void setAdminPage(String adminPage) {
    this.adminPage = adminPage;
  }

  public List<FormReviewHistory> getFormReviewHistory() {
    return formReviewHistory;
  }

  public void setFormReviewHistory(List<FormReviewHistory> formReviewHistory) {
    this.formReviewHistory = formReviewHistory;
  }

  public String getReviewComments() {
    return reviewComments;
  }

  public void setReviewComments(String reviewComments) {
    this.reviewComments = reviewComments;
  }

  public String getPageScript() {
    return pageScript;
  }

  public void setPageScript(String pageScript) {
    this.pageScript = pageScript;
  }

  public List<Village> getVillages() {
    return villages;
  }

  public void setVillages(List<Village> villages) {
    this.villages = villages;
  }

  public List<Socity> getSocities() {
    return socities;
  }

  public void setSocities(List<Socity> socities) {
    this.socities = socities;
  }

  public List<Socity> getSocitiesBasedOnVillages() {
    return socitiesBasedOnVillages;
  }

  public void setSocitiesBasedOnVillages(List<Socity> socitiesBasedOnVillages) {
    this.socitiesBasedOnVillages = socitiesBasedOnVillages;
  }

  public Part getFile() {
    return file;
  }

  public void setFile(Part file) {
    this.file = file;
  }

  public String getFileUploadFlag() {
    return fileUploadFlag;
  }

  public void setFileUploadFlag(String fileUploadFlag) {
    this.fileUploadFlag = fileUploadFlag;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getPhotoUniqueName() {
    return photoUniqueName;
  }

  public void setPhotoUniqueName(String photoUniqueName) {
    this.photoUniqueName = photoUniqueName;
  }

  public List<FormAttachment> getFormAttachemts() {
    return formAttachemts;
  }

  public void setFormAttachemts(List<FormAttachment> formAttachemts) {
    this.formAttachemts = formAttachemts;
  }

  @PostConstruct
  public void init() {
    try {
      pageFlag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
          .get("pageFlag");
      loadVillages();
      loadSocity();
      if ("new".equals(pageFlag)) {
        form = new Form();
        fileType = "photo";
        form.setType("spl_allowance");
      } else if ("revieviewExistingForm".equals(pageFlag)) {
        this.formCode = FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap().get("formCode");
        adminPage = "adminView";
        loadForm();

      } else if ("viewExistingForm".equals(pageFlag)) {
        adminPage = "search";
      }
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }
  }

  private void loadVillageBasedSocity() {
    if (form != null && form.getVillage() != null && CollectionUtils.isNotEmpty(socities)) {
      socitiesBasedOnVillages = socities.stream()
          .filter(so -> so.getVillageId().equals(form.getVillage())).collect(Collectors.toList());
    }

  }

  private void loadSocity() throws BusinessException {
    socities = formService.getAllSocity();
  }

  private void loadVillages() throws BusinessException {
    villages = formService.getAllVillages();
  }

  public void loadForm() {
    try {
      form = formService.getForm(formCode);
      formAttachemts = formService.getFormAttachements(formCode);
      formReviewHistory = formService.getFormReviewHistory(formCode);

      if (CollectionUtils.isNotEmpty(formAttachemts)) {
        photoUniqueName = formAttachemts.stream().filter(fa -> fa.getFileType().equals("photo"))
            .map(FormAttachment::getUniqueName).findFirst().orElse(null);
      }

      if (form != null) {
        pageFlag = "view";
      } else {
        Utils.addErrorMessage("Form code not found.");
      }
      fileType = "photo";
      loadVillageBasedSocity();
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void save() {
    try {
      formService.saveForm(form, formAttachemts);
      pageFlag = "view";
      pageScript = "formSave";
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void reviewForm(String status) {
    try {
      FormReviewHistory his = new FormReviewHistory();
      his.setComments(reviewComments);
      his.setStatus(status);
      his.setFormId(form.getId());
      his.setReviewedBy(((User) FacesContext.getCurrentInstance().getExternalContext()
          .getSessionMap().get("user")).getId());
      his.setReviewedOn(new Date());
      formService.saveReviewHistory(his);
      Utils.addInfoMessage("Form status updated successfully.");
      loadForm();
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void download() throws IOException {
    try {
      if (this.form != null) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset();
        ec.setResponseHeader("Content-Disposition",
            "attachment; filename=\"" + form.getFormCode() + ".pdf\"");

        createPdf(ec.getResponseOutputStream(), form);
        fc.responseComplete();
      }
    } catch (Exception e) {
      e.printStackTrace();
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void createPdf(OutputStream output, Form frm) throws Exception {
    String baseLocataion = Utils.getValueFromAppProperties("aspose_font_and_folder");
    FontSettings.getDefaultInstance().setFontsFolder(baseLocataion + "fonts", true);
    String yes = getValueFromPageLabels("yes");
    String no = getValueFromPageLabels("no");

    Map<String, Object> map = new LinkedHashMap<>();

    map.put("year", Year.now().toString());
    map.put("formCode", frm.getFormCode());

    map.put("village", frm.getVillageName());
    map.put("taluk", frm.getTaluk());
    map.put("district", frm.getDistrict());

    map.put("name", frm.getName());
    map.put("fathersName", frm.getFathersName());
    map.put("age", frm.getAge());
    map.put("incomeDet", frm.getIncomeDet());
    if (frm.getMarried() != null) {
      map.put("married", frm.getMarried() ? yes : no);
    }

    if (frm.getSection() != null) {
      String section = getValueFromPageLabels(frm.getSection());
      map.put("section", section);
    }
    map.put("address", frm.getAddress());
    map.put("biometricId", frm.getBiometricId());
    map.put("biometricAppFormDetails", frm.getBiometricAppFormDetails());
    map.put("rationCardId", frm.getRationCardId());
    map.put("voterId", frm.getVoterId());
    map.put("adhaarNo", frm.getAdhaarNo());
    map.put("nationalBankDetails", frm.getNationalBankDetails());
    map.put("groupAccDetails", frm.getGroupAccDetails());
    map.put("cooperativeBankDetails", frm.getCooperativeBankDetails());
    map.put("tnFishAssociationDetails", frm.getTnFishAssociationDetails());
    map.put("workType", frm.getWorkType());
    if (!Objects.isNull(frm.getIsPrevYearbenefitter())) {
      map.put("isPrevYearbenefitter", frm.getIsPrevYearbenefitter() ? yes : no);
    }
    map.put("benefittedYear", frm.getBenefittedYear());
    if (!Objects.isNull(frm.getIsBenefitter())) {
      map.put("isBenefitter", frm.getIsBenefitter() ? yes : no);
    }
    if (!Objects.isNull(frm.getIsGettingReliefFund())) {
      map.put("isGettingReliefFund", frm.getIsGettingReliefFund() ? yes : no);
    }
    if (!Objects.isNull(frm.getIsFullTimeFisherman())) {
      map.put("isFullTimeFisherman", frm.getIsFullTimeFisherman() ? yes : no);
    }
    String fileLoc;
    if ("spl_allowance".equals(frm.getType())) {
      fileLoc = File.separator + "template" + File.separator + "spl_allowance.docx";
    } else if ("fishing_ban_relief".equals(frm.getType())) {
      fileLoc = File.separator + "template" + File.separator + "fishing_ban_relief.docx";
    } else if ("nfsrs".equals(frm.getType())) {
      fileLoc = File.separator + "template" + File.separator + "nfsrs.docx";
    } else {
      fileLoc = File.separator + "template" + File.separator + "nfsrs_woman.docx";
    }
    Document doc = new Document(baseLocataion + fileLoc);


    if (photoUniqueName != null) {
      int width = 120;
      int height = 150;
      BufferedImage bi = ImageIO
          .read(new File(Utils.getValueFromAppProperties("user_file_location") + photoUniqueName));
      int type = bi.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bi.getType();
      BufferedImage ri = new BufferedImage(width, height, type);
      Graphics2D g = ri.createGraphics();
      g.drawImage(bi, 0, 0, width, height, null);
      g.dispose();

      DocumentBuilder builder = new DocumentBuilder(doc);
      builder.moveToMergeField("photo");
      // Shape img = builder
      // .insertImage(Utils.getValueFromAppProperties("user_file_location") + photoUniqueName);

      Shape img = builder.insertImage(ri);
      img.setWidth(width);
      img.setHeight(height);

    }



    doc.getMailMerge().execute(map.keySet().toArray(new String[0]), map.values().toArray());
    doc.save(output, SaveFormat.PDF);
  }

  private String getValueFromPageLabels(String key) {
    String val = key;
    try {
      ResourceBundle bundle = ResourceBundle.getBundle("pageLabels");
      val = bundle.getString(key);
    } catch (Exception e) {
      System.out.println("key not found");
    }
    return val;
  }

  public void updateVillage() {
    try {
      if (this.form.getVillage() != null) {
        loadVillageBasedSocity();
        Village vil = villages.stream().filter(vi -> vi.getId().equals(this.form.getVillage()))
            .findAny().orElse(null);
        if (vil != null) {
          this.form.setVillageName(vil.getName());
        }
      }
    } catch (Exception e) {
      Utils.addErrorMessage(e.getMessage());
    }

  }

  public void updatesocity() {
    try {
      if (this.form.getSocity() != null) {
        Socity socity = socities.stream().filter(s -> s.getId().equals(form.getSocity()))
            .findFirst().orElse(null);
        if (socity != null) {
          form.setSocityName(socity.getName());

          form.setSocityCode(socity.getCode());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void fileUpload() {
    try {
      String fileName = file.getSubmittedFileName();
      String extension = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
      String uniqueName = Utils.getEncryptedString(file.getName() + new Date()) + extension;

      if ("photo".equals(fileType)) {
        photoUniqueName = uniqueName;
        if (formAttachemts != null) {
          FormAttachment fad = formAttachemts.stream()
              .filter(fa -> "photo".equals(fa.getFileType())).findFirst().orElse(null);
          if (fad != null) {
            Files.deleteIfExists(Paths.get(Utils.getValueFromAppProperties("user_file_location"),
                fad.getUniqueName()));
            formAttachemts.remove(fad);
          }
        }
      }

      FormAttachment fa = new FormAttachment();
      fa.setFileName(fileName);
      fa.setFileType(fileType);
      fa.setUniqueName(uniqueName);

      if (formAttachemts == null) {
        formAttachemts = new ArrayList<>();
      }

      formAttachemts.add(fa);

      Path uploadPath =
          Paths.get(Utils.getValueFromAppProperties("user_file_location"), uniqueName);
      Files.copy(file.getInputStream(), uploadPath);
    } catch (Exception e) {
      e.printStackTrace();
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void downloadUploadedFile(FormAttachment formAttachment) throws IOException {
    try {
      FacesContext fc = FacesContext.getCurrentInstance();
      ExternalContext ec = fc.getExternalContext();

      ec.responseReset();
      ec.setResponseHeader("Content-Disposition",
          "attachment; filename=\"" + formAttachment.getFileName() + "\"");

      Path uploadPath = Paths.get(Utils.getValueFromAppProperties("user_file_location"),
          formAttachment.getUniqueName());
      Files.copy(uploadPath, ec.getResponseOutputStream());

      fc.responseComplete();

    } catch (Exception e) {
      e.printStackTrace();
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void deleteUploadedFile(int index) throws IOException {
    try {

      FormAttachment fa = formAttachemts.get(index);
      if ("photo".equals(fa.getFileType())) {
        photoUniqueName = null;
      }
      Files.deleteIfExists(
          Paths.get(Utils.getValueFromAppProperties("user_file_location"), fa.getUniqueName()));
      formAttachemts.remove(index);
    } catch (Exception e) {
      e.printStackTrace();
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public boolean isMessageEmpty() {
    return FacesContext.getCurrentInstance().getMessageList().isEmpty();
  }
}
