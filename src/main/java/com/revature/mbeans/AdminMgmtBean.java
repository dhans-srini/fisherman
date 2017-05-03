package com.revature.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null) {
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

}
