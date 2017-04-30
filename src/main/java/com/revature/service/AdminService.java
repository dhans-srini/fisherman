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

	private final String userInsertQuery = "insert into users (user_name,password) values(?,?)";
	private final String userSelectQuery = "select id,user_name,password from users u where u.user_name=?";
	private final String userUpdQuery = "update users u set u.last_login_time=? where u.user_name=?";
	private final String allFormQuery = "select id,code,first_name, last_name, age,created_on from form order by created_on desc";

	public Long validateUser(String userName, String password) throws BusinessException {
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
			byte[] dbPwd = null;
			Long userId = null;
			while (rs.next()) {
				userId = rs.getLong("id");
				dbPwd = rs.getBytes("password");
			}

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] gnPwd = md.digest();
			boolean isValidUser = Arrays.equals(gnPwd, dbPwd);
			if (!isValidUser) {
				throw new BusinessException("Authentication failed.");
			}
			PreparedStatement psUpd = con.prepareStatement(userUpdQuery);
			psUpd.setObject(1, new Date());
			psUpd.setString(2, userName);
			psUpd.executeUpdate();
			return userId;
		} catch (Exception e) {
			throw new BusinessException("User authentication failed.", e);
		}
	}

	public void createUser(Connection con) throws SQLException, NoSuchAlgorithmException {
		PreparedStatement ps1 = con.prepareStatement(userInsertQuery);

		MessageDigest md1 = MessageDigest.getInstance("MD5");
		md1.update("Pass123$".getBytes());
		byte[] bytes = md1.digest();
		ps1.setString(1, "admin");
		ps1.setObject(2, bytes);
		ps1.executeUpdate();
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
				form.setFirstName(rs.getString("first_name"));
				form.setLastName(rs.getString("last_name"));
				form.setAge(rs.getString("age"));
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

}
