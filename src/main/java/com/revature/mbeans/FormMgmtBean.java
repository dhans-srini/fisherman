package com.revature.mbeans;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.SaveFormat;
import com.revature.models.Form;
import com.revature.models.FormReviewHistory;
import com.revature.models.User;
import com.revature.service.FormService;
import com.revature.utils.Utils;

@ManagedBean
@ViewScoped
public class FormMgmtBean {

	private static final String FONT = "FreeSans.ttf";
	private FormService formService = FormService.getInstance();
	private Form form;
	private String formCode;

	private String pageFlag;
	private String adminPage;
	private List<FormReviewHistory> formReviewHistory;

	private String reviewComments;

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

	@PostConstruct
	public void init() {
		pageFlag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pageFlag");
		if ("new".equals(pageFlag)) {
			form = new Form();
			form.setType("spl_allowance");
		} else if ("revieviewExistingForm".equals(pageFlag)) {
			this.formCode = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("formCode");
			adminPage = "adminView";
			loadForm();
		}
	}

	public void loadForm() {
		try {
			form = formService.getForm(formCode);
			formReviewHistory = formService.getFormReviewHistory(formCode);
			if (form != null) {
				pageFlag = "view";
			} else {
				Utils.addErrorMessage("Form code not found.");
			}
		} catch (Exception e) {
			Utils.addErrorMessage(e.getMessage());
		}
	}

	public void save() {
		try {
		//	formService.saveForm(form);
			pageFlag = "view";
			Utils.addInfoMessage("Form saved successfully");
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
			his.setReviewedBy(
					((User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"))
							.getId());
			his.setReviewedOn(new Date());
			formService.saveReviewHistory(his);
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
				ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + form.getFormCode() + ".pdf\"");

				createPdf(ec.getResponseOutputStream(), form);
				fc.responseComplete();
			}
		} catch (Exception e) {
			Utils.addErrorMessage(e.getMessage());
		}
	}

	public void createPdf(OutputStream output, Form frm) throws Exception {

		String baseLocataion = "E:\\Revature Development\\Git workspace\\fisherman-govt\\";
		FontSettings.getDefaultInstance().setFontsFolder(baseLocataion + "fonts", true);

		Set<String> keys = new LinkedHashSet<>();
		keys.add("name");
		keys.add("father_name");
		keys.add("address");
		List<String> values = new LinkedList<>();
		values.add(frm.getName());
		values.add(frm.getFathersName());
		values.add(frm.getSection());
		Document doc = new Document(baseLocataion + "\\template\\inori.docx");
		doc.getMailMerge().execute(keys.toArray(new String[0]), values.toArray());
		doc.save(output, SaveFormat.PDF);
	}
}
