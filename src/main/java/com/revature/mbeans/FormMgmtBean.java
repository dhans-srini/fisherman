package com.revature.mbeans;

import java.io.IOException;
import java.io.OutputStream;

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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.revature.models.Form;
import com.revature.service.FormService;
import com.revature.utils.Utils;

@ManagedBean
@ViewScoped
public class FormMgmtBean {

  private FormService formService = FormService.getInstance();
  private Form form;
  private String formCode;

  private String pageFlag;

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

  @PostConstruct
  public void init() {
    pageFlag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
        .get("pageFlag");
    if ("new".equals(pageFlag)) {
      form = new Form();
    } else if ("viewExistingForm".equals(pageFlag)) {
    } else if ("edit".equals(pageFlag)) {

    }
  }

  public void loadForm() {
    try {
      form = formService.getForm(formCode);
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
      Utils.addErrorMessage(e.getMessage());
    }
  }

  public void createPdf(OutputStream output, Form frm) throws DocumentException, IOException {
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
    table.setTotalWidth(new float[] {72, 216});
    Phrase p;
    PdfPCell cell;

    table.addCell("First Name");
    p = new Phrase(frm.getFirstName());
    cell = new PdfPCell(p);
    table.addCell(cell);

    table.addCell("Last Name");
    p = new Phrase(frm.getLastName());
    cell = new PdfPCell(p);
    table.addCell(cell);

    table.addCell("Age");
    p = new Phrase(frm.getAge());
    cell = new PdfPCell(p);
    table.addCell(cell);


    document.add(table);
    document.close();
  }
}
