package com.revature.models;

import java.util.Date;

public class FormReviewHistory {

	private Long id;
	private String comments;
	private String status;
	private Long formId;
	private Long reviewedBy;
	private Date reviewedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public Long getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(Long reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public Date getReviewedOn() {
		return reviewedOn;
	}

	public void setReviewedOn(Date reviewedOn) {
		this.reviewedOn = reviewedOn;
	}
}
