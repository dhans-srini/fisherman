package com.revature.mbeans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.revature.models.User;
import com.revature.service.AdminService;
import com.revature.utils.Utils;

@ManagedBean
@ViewScoped
public class UserMgmtBean {
	private AdminService adminService = AdminService.getInstance();
	private List<User> users;
	private User user;
	private String page;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@PostConstruct
	public void init() {
		try {
			page = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page");
			if ("add".equals(page)) {
				user = new User();
			} else if ("edit".equals(page)) {
				user = adminService.getUserById(Long.parseLong(
						FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId")));
			} else {
				User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
				users = adminService.getAllUsers(user);
			}
		} catch (Exception e) {
			Utils.addErrorMessage(e.getMessage());
		}
	}

	public void saveUser() {
		try {
			if (!user.getPassword().equals(user.getConfirmPwd())) {
				Utils.addErrorMessage("Password and confirm password mismatch.");
			}
			if (FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
				User creagedBy = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get("user");
				adminService.saveUser(user, creagedBy);
				Utils.addInfoMessage("User saved successfully");
				redirectToUsers();
			}
		} catch (Exception e) {
			Utils.addErrorMessage(e.getMessage());
		}
	}

	public void updateUser() {
		try {
			if (user.getPassword() != null && !user.getPassword().equals(user.getConfirmPwd())) {
				Utils.addErrorMessage("Password and confirm password mismatch.");
			}
			if (FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
				User updatedBy = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get("user");
				adminService.updateUser(user, updatedBy);
				Utils.addInfoMessage("User updated successfully");
				redirectToUsers();
			}
		} catch (Exception e) {
			Utils.addErrorMessage(e.getMessage());
		}
	}

	private void redirectToUsers() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext().redirect("users.xhtml");
	}

}
