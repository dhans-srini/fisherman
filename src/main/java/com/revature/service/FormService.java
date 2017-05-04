package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.revature.exception.BusinessException;
import com.revature.models.Form;
import com.revature.models.FormReviewHistory;
import com.revature.models.Socity;
import com.revature.models.Village;

public class FormService {

	private static FormService instance;

	private FormService() {
	}

	public static FormService getInstance() {
		if (instance == null) {
			synchronized (FormService.class) {
				if (instance == null) {
					instance = new FormService();
				}
			}
		}
		return instance;

	}

	private final static String insertTableSQL = "INSERT INTO form (name, fathers_name, is_married,section,created_on,status,code,address,biometric_id,"
			+ "ration_card_id,voter_id,adhaar_no,national_bank_details,group_acc_dtls,coop_bank_dtl,"
			+ "tn_fish_association_dtl,work_type,is_prev_year_benefitter,benefitted_year,is_benefitter,"
			+ "is_getting_relief_fund,type,age,income_det,bio_metric_app_form_det,is_full_time_fisherman,"
			+ "village,taluk,district,socity) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private final static String insertFormHistorySQL = "INSERT INTO form_review_history "
			+ "( comments,status, reviewed_by, form,reviewed_on) VALUES (?,?,?,?,?)";
	private final static String updateTableSQL = "update form set name=?,fathers_name=?,is_married=?,section=?,updated_on=?,status=?,"
			+ "code=?,address=?,biometric_id=?,ration_card_id=?,voter_id=?,adhaar_no=?,"
			+ "national_bank_details=?,group_acc_dtls=?,coop_bank_dtl=?,tn_fish_association_dtl=?,"
			+ "work_type=?,is_prev_year_benefitter=?,benefitted_year=?,is_benefitter=?,is_getting_relief_fund=?,"
			+ "age=?,income_det=?,bio_metric_app_form_det=?,is_full_time_fisherman=?"
			+ ",village=?,taluk=?,district=?,socity=?  where id=?";
	private final static String formByCode = "select f.id,f.code,f.name,f.age,f.type, f.fathers_name,f.income_det,f.is_full_time_fisherman,f.bio_metric_app_form_det, "
			+ "f.is_married,f.section,f.created_on,f.status,f.address,f.biometric_id,"
			+ "f.ration_card_id,f.voter_id,f.adhaar_no,f.national_bank_details,f.group_acc_dtls,f.coop_bank_dtl,"
			+ "f.tn_fish_association_dtl,f.work_type,f.is_prev_year_benefitter,f.benefitted_year,f.is_benefitter,"
			+ "f.is_getting_relief_fund,f.village,f.district,f.taluk, "
			+ "v.name villageName,f.socity, s.name socityName,s.code societyCode from form f "
			+ "left join villages v on v.id=f.village=v.id " + "left join societies s on s.id=f.socity where f.code=?";
	private final static String formReviewHistoryByCode = "select his.id,his.status, his.comments "
			+ "from form f join form_review_history his on his.form=f.id where f.code=?";
	private final static String updateFormStatusSQL = "update form set status=? where id=?";

	private static final String allVillageQuery = "select id,name from villages";

	private static final String allSocityQuery = "select id,name,code,village_id,for_male,for_female from societies";

	public Form saveForm(Form form) throws BusinessException {
		try {
			Connection con = getConnection();
			form.setStatus("submitted");
			if (form.getId() == null) {
				PreparedStatement ps = con.prepareStatement(insertTableSQL);
				String formCode = "02/" + form.getSocityCode() + "/";
				formCode += UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

				form.setFormCode(formCode);
				ps.setObject(1, form.getName());
				ps.setObject(2, form.getFathersName());
				ps.setObject(3, form.getMarried());
				ps.setObject(4, form.getSection());
				ps.setObject(5, new Date());
				ps.setObject(6, form.getStatus());
				ps.setObject(7, form.getFormCode());
				ps.setObject(8, form.getAddress());
				ps.setObject(9, form.getBiometricId());
				ps.setObject(10, form.getRationCardId());
				ps.setObject(11, form.getVoterId());
				ps.setObject(12, form.getAdhaarNo());
				ps.setObject(13, form.getNationalBankDetails());
				ps.setObject(14, form.getGroupAccDetails());
				ps.setObject(15, form.getCooperativeBankDetails());
				ps.setObject(16, form.getTnFishAssociationDetails());
				ps.setObject(17, form.getWorkType());
				ps.setObject(18, form.getIsPrevYearbenefitter());
				ps.setObject(19, form.getBenefittedYear());
				ps.setObject(20, form.getIsBenefitter());
				ps.setObject(21, form.getIsGettingReliefFund());
				ps.setObject(22, form.getType());
				ps.setObject(23, form.getAge());
				ps.setObject(24, form.getIncomeDet());
				ps.setObject(25, form.getBiometricAppFormDetails());
				ps.setObject(26, form.getIsFullTimeFisherman());

				ps.setObject(27, form.getVillage());
				ps.setObject(28, form.getTaluk());
				ps.setObject(29, form.getDistrict());
				ps.setObject(30, form.getSocity());
				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					form.setId((Long) rs.getObject(1));
				}
			} else {
				PreparedStatement ps = con.prepareStatement(updateTableSQL);
				ps.setObject(1, form.getName());
				ps.setObject(2, form.getFathersName());
				ps.setObject(3, form.getMarried());
				ps.setObject(4, form.getSection());
				ps.setObject(5, new Date());
				ps.setObject(6, form.getStatus());
				ps.setObject(7, form.getFormCode());
				ps.setObject(8, form.getAddress());
				ps.setObject(9, form.getBiometricId());
				ps.setObject(10, form.getRationCardId());
				ps.setObject(11, form.getVoterId());
				ps.setObject(12, form.getAdhaarNo());
				ps.setObject(13, form.getNationalBankDetails());
				ps.setObject(14, form.getGroupAccDetails());
				ps.setObject(15, form.getCooperativeBankDetails());
				ps.setObject(16, form.getTnFishAssociationDetails());
				ps.setObject(17, form.getWorkType());
				ps.setObject(18, form.getIsPrevYearbenefitter());
				ps.setObject(19, form.getBenefittedYear());
				ps.setObject(20, form.getIsBenefitter());
				ps.setObject(21, form.getIsGettingReliefFund());
				ps.setObject(22, form.getAge());
				ps.setObject(23, form.getIncomeDet());
				ps.setObject(24, form.getBiometricAppFormDetails());
				ps.setObject(25, form.getIsFullTimeFisherman());

				ps.setObject(26, form.getVillage());
				ps.setObject(27, form.getTaluk());
				ps.setObject(28, form.getDistrict());
				ps.setObject(29, form.getSocity());

				ps.setObject(30, form.getId());
				ps.executeUpdate();
			}

		} catch (Exception e) {
			throw new BusinessException("Form save failed.", e);
		}
		return form;
	}

	public Form getForm(String formCode) throws BusinessException {
		Form form = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(formByCode);

			ps.setString(1, formCode);
			ResultSet rs = ps.executeQuery();

			int rowcount = 0;
			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}
			if (rowcount != 0) {
				form = new Form();
			}

			while (rs.next()) {
				form.setId(rs.getLong("id"));
				form.setType(rs.getString("type"));
				form.setFormCode(rs.getString("code"));
				form.setName(rs.getString("name"));
				form.setFathersName(rs.getString("fathers_name"));
				form.setAge(rs.getString("age"));
				form.setIncomeDet(rs.getString("income_det"));
				form.setMarried(rs.getBoolean("is_married"));
				form.setSection(rs.getString("section"));
				form.setCreatedOn(rs.getTimestamp("created_on"));
				form.setStatus(rs.getString("status"));
				form.setAddress(rs.getString("address"));
				form.setAdhaarNo(rs.getString("adhaar_no"));
				form.setBiometricId(rs.getString("biometric_id"));
				form.setBiometricAppFormDetails(rs.getString("bio_metric_app_form_det"));
				form.setRationCardId(rs.getString("ration_card_id"));
				form.setVoterId(rs.getString("voter_id"));
				form.setNationalBankDetails(rs.getString("national_bank_details"));
				form.setGroupAccDetails(rs.getString("group_acc_dtls"));
				form.setCooperativeBankDetails(rs.getString("coop_bank_dtl"));
				form.setTnFishAssociationDetails(rs.getString("tn_fish_association_dtl"));
				form.setWorkType(rs.getString("work_type"));
				form.setIsPrevYearbenefitter(rs.getBoolean("is_prev_year_benefitter"));
				form.setBenefittedYear(rs.getString("benefitted_year"));
				form.setIsBenefitter(rs.getBoolean("is_benefitter"));
				form.setIsGettingReliefFund(rs.getBoolean("is_getting_relief_fund"));
				form.setIsFullTimeFisherman(rs.getBoolean("is_full_time_fisherman"));
				form.setVillage(rs.getLong("village"));
				form.setVillageName(rs.getString("villageName"));
				form.setTaluk(rs.getString("taluk"));
				form.setDistrict(rs.getString("district"));

				form.setSocity(rs.getLong("socity"));
				form.setSocityName(rs.getString("socityName"));
				form.setSocityCode(rs.getString("societyCode"));

			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			throw new BusinessException("Form get failed.", e);
		}
		return form;
	}

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/fisher_man?autoReconnect=true&zeroDateTimeBehavior=convertToNull", "root",
				"root");
	}

	public List<FormReviewHistory> getFormReviewHistory(String formCode) throws BusinessException {
		List<FormReviewHistory> formReviewHistories = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(formReviewHistoryByCode);

			ps.setString(1, formCode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FormReviewHistory history = new FormReviewHistory();
				history.setId(rs.getLong("id"));
				history.setComments(rs.getString("comments"));
				history.setStatus(rs.getString("status"));
				formReviewHistories.add(history);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			throw new BusinessException("Form History get failed.", e);
		}
		return formReviewHistories;
	}

	public FormReviewHistory saveReviewHistory(FormReviewHistory his) throws BusinessException {
		try {
			Connection con = getConnection();

			PreparedStatement ps = con.prepareStatement(insertFormHistorySQL);
			ps.setString(1, his.getComments());
			ps.setString(2, his.getStatus());
			ps.setLong(3, his.getReviewedBy());
			ps.setLong(4, his.getFormId());
			ps.setObject(5, new Date());
			ps.executeUpdate();

			ps = con.prepareStatement(updateFormStatusSQL);
			ps.setString(1, his.getStatus());
			ps.setLong(2, his.getFormId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new BusinessException("Form save failed.", e);
		}
		return his;
	}

	public List<Village> getAllVillages() throws BusinessException {
		List<Village> villages = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(allVillageQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Village village = new Village();
				village.setId(rs.getLong("id"));
				village.setName(rs.getString("name"));
				villages.add(village);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Villages get failed.", e);
		}
		return villages;
	}

	public List<Socity> getAllSocity() throws BusinessException {
		List<Socity> socities = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(allSocityQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Socity socity = new Socity();
				socity.setId(rs.getLong("id"));
				socity.setName(rs.getString("name"));
				socity.setCode(rs.getString("code"));
				socity.setVillageId(rs.getLong("village_id"));
				socity.setIsMale(rs.getBoolean("for_male"));
				socity.setIsFemale(rs.getBoolean("for_female"));
				socities.add(socity);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Villages get failed.", e);
		}
		return socities;
	}

}
