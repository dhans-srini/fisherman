package com.revature.mbeans;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.revature.models.Form;
import com.revature.models.FormReviewHistory;
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
			formService.saveForm(form);
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
					(Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
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

	public void createPdf(OutputStream output, Form frm) throws DocumentException, IOException {

		ResourceBundle mybundle = ResourceBundle.getBundle("pageLabels");

		Document document = new Document(PageSize.A5.rotate());
		PdfWriter.getInstance(document, output);
		document.open();

		Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
		Chunk chunk = new Chunk("Fisher man", chapterFont);
		Paragraph paragraph = new Paragraph(chunk);
		paragraph.setAlignment(Element.ALIGN_CENTER);

		Chapter chapter = new Chapter(paragraph, 1);
		chapter.setNumberDepth(0);
		document.add(chapter);

		document.add(Chunk.NEWLINE);

		// creating form fields
		PdfPTable table = new PdfPTable(2);
		table.setTotalWidth(new float[] { 72, 216 });
		Phrase p;
		PdfPCell cell;
		Font f = FontFactory.getFont("D:\\FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		table.addCell(new Phrase(mybundle.getString("name"), f));
		p = new Phrase(frm.getName());
		cell = new PdfPCell(p);
		table.addCell(cell);

		table.addCell(new Phrase(mybundle.getString("father_name"), f));
		p = new Phrase(frm.getFathersName());
		cell = new PdfPCell(p);
		table.addCell(cell);

		table.addCell(new Phrase(mybundle.getString("section"), f));
		p = new Phrase(frm.getSection());
		cell = new PdfPCell(p);
		table.addCell(cell);

		table.addCell(new Phrase(mybundle.getString("is_married"), f));
		p = new Phrase(frm.getMarried() ? mybundle.getString("yes") : mybundle.getString("no"), f);
		cell = new PdfPCell(p);
		table.addCell(cell);

		document.add(table);
		document.close();
	}
}
