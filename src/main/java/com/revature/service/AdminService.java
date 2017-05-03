package com.revature.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.revature.exception.BusinessException;
import com.revature.models.Form;
import com.revature.models.User;

public class AdminService {

	private static AdminService instance;

	private AdminService() {
	}

	public static AdminService getInstance() {
		if (instance == null) {
			synchronized (AdminService.class) {
				if (instance == null) {
					instance = new AdminService();
				}
			}
		}
		return instance;

	}

	private Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/fisher_man?autoReconnect=true&zeroDateTimeBehavior=convertToNull", "root",
				"root");
	}

	private final String userInsertQuery = "insert into users (name,user_name,password,created_by,is_active) "
			+ "values(?,?,?,?,?)";
	private final String userUpdWithAllFieldsQuery = "update users u set u.name=?,u.user_name=?,u.password=?,u.updated_by=?,"
			+ "u.is_active=? where u.id=?";
	private final String userUpdWithoutPwdQuery = "update users u set u.name=?,u.user_name=?,u.updated_by=?,"
			+ "u.is_active=? where u.id=?";
	private final String userSelectQuery = "select id,name,user_name,password,is_admin,is_active from users u where u.user_name=?";
	private final String userByIdSelectQuery = "select id,name,user_name,password,is_admin,is_active from users u where u.id=?";
	private final String userUpdQuery = "update users u set u.last_login_time=? where u.user_name=?";
	private final String allFormQuery = "select id,code,name, fathers_name, is_married,section,created_on,status "
			+ "from form f order by f.created_on";
	private final String allUserQuery = "select id,name,user_name,is_admin,is_active from users u where u.id!=?";

	public User validateUser(String userName, String password) throws BusinessException {
		try {
			Connection con = getConnection();
			// createUser(con);
			PreparedStatement ps = con.prepareStatement(userSelectQuery);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			int rowcount = 0;
			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}
			if (rowcount == 0) {
				throw new BusinessException("User name not found");
			}
			User user = new User();
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setPwd(rs.getBytes("password"));
				user.setName(rs.getString("name"));
				user.setUserName(rs.getString("user_name"));
				user.setIsAdmin(rs.getBoolean("is_admin"));
				user.setIsActive(rs.getBoolean("is_active"));
			}

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] gnPwd = md.digest();
			boolean isValidUser = Arrays.equals(gnPwd, user.getPwd());
			if (!isValidUser) {
				throw new BusinessException("Authentication failed.");
			}
			PreparedStatement psUpd = con.prepareStatement(userUpdQuery);
			psUpd.setObject(1, new Date());
			psUpd.setString(2, userName);
			psUpd.executeUpdate();
			return user;
		} catch (Exception e) {
			throw new BusinessException("User authentication failed.", e);
		}
	}

	public void createUser(Connection con, User user, User creagedBy) throws SQLException, NoSuchAlgorithmException {
		PreparedStatement ps1 = con.prepareStatement(userInsertQuery);

		MessageDigest md1 = MessageDigest.getInstance("MD5");
		md1.update(user.getPassword().getBytes());
		byte[] bytes = md1.digest();
		ps1.setString(1, user.getName());
		ps1.setString(2, user.getUserName());
		ps1.setObject(3, bytes);
		ps1.setObject(4, creagedBy.getId());
		ps1.setObject(5, user.getIsActive());
		ps1.executeUpdate();
	}

	public void updateUser(Connection con, User user, User updatedBy) throws SQLException, NoSuchAlgorithmException {

		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			PreparedStatement ps1 = con.prepareStatement(userUpdWithAllFieldsQuery);
			MessageDigest md1 = MessageDigest.getInstance("MD5");
			md1.update(user.getPassword().getBytes());
			byte[] bytes = md1.digest();
			ps1.setString(1, user.getName());
			ps1.setString(2, user.getUserName());
			ps1.setObject(3, bytes);
			ps1.setObject(4, updatedBy.getId());
			ps1.setObject(5, user.getIsActive());
			ps1.setObject(6, user.getId());
			ps1.executeUpdate();
		} else {
			PreparedStatement ps1 = con.prepareStatement(userUpdWithoutPwdQuery);
			ps1.setString(1, user.getName());
			ps1.setString(2, user.getUserName());
			ps1.setObject(3, updatedBy.getId());
			ps1.setObject(4, user.getIsActive());
			ps1.setObject(5, user.getId());
			ps1.executeUpdate();
		}

	}

	public List<Form> getAllForms() throws BusinessException {
		List<Form> forms = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(allFormQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Form form = new Form();
				form.setId(rs.getLong("id"));
				form.setFormCode(rs.getString("code"));
				form.setName(rs.getString("name"));
				form.setFathersName(rs.getString("fathers_name"));
				form.setMarried(rs.getBoolean("is_married"));
				form.setSection(rs.getString("section"));
				form.setStatus(rs.getString("status"));
				form.setCreatedOn(rs.getTimestamp("created_on"));
				forms.add(form);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			throw new BusinessException("Forms get failed.", e);
		}
		return forms;
	}

	public List<User> getAllUsers(User user) throws BusinessException {
		List<User> users = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(allUserQuery);
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User userToAdd = new User();
				userToAdd.setId(rs.getLong("id"));
				userToAdd.setName(rs.getString("name"));
				userToAdd.setUserName(rs.getString("user_name"));
				userToAdd.setIsAdmin(rs.getBoolean("is_admin"));
				userToAdd.setIsAdmin(rs.getBoolean("is_active"));
				users.add(userToAdd);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			throw new BusinessException("Users get failed.", e);
		}
		return users;
	}

	public void saveUser(User user, User creagedBy) throws BusinessException {
		try {
			Connection con = getConnection();
			createUser(con, user, creagedBy);
		} catch (SQLException e) {
			throw new BusinessException("User Name already exist.", e);
		} catch (Exception e) {
			throw new BusinessException("User save failed.", e);
		}
	}

	public void updateUser(User user, User updatedBy) throws BusinessException {
		try {
			Connection con = getConnection();
			updateUser(con, user, updatedBy);
		} catch (SQLException e) {
			throw new BusinessException("User Name already exist.", e);
		} catch (Exception e) {
			throw new BusinessException("User save failed.", e);
		}
	}

	public User getUserById(Long userId) throws BusinessException {
		try {

			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(userByIdSelectQuery);
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			int rowcount = 0;
			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}
			if (rowcount == 0) {
				throw new BusinessException("User name not found");
			}
			User user = new User();
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setPwd(rs.getBytes("password"));
				user.setName(rs.getString("name"));
				user.setUserName(rs.getString("user_name"));
				user.setIsAdmin(rs.getBoolean("is_admin"));
				user.setIsActive(rs.getBoolean("is_active"));
			}
			return user;
		} catch (Exception e) {
			throw new BusinessException("User retrieval failed.", e);
		}
	}

}
