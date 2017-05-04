package com.revature.models;

import java.sql.Timestamp;

public class Form {

	private Long id;
	private String formCode;
	private String type;
	private String name;
	private String fathersName;
	private String age;
	private String incomeDet;
	private Boolean married;
	private String section;
	private Timestamp createdOn;
	private String address;
	private String biometricId;
	private String biometricAppFormDetails;
	private String rationCardId;
	private String voterId;
	private String adhaarNo;
	private String nationalBankDetails;
	private String groupAccDetails;
	private String cooperativeBankDetails;
	private String tnFishAssociationDetails;
	private String workType;
	private Boolean isPrevYearbenefitter;
	private String benefittedYear;
	private Boolean isBenefitter;
	private Boolean isGettingReliefFund;
	private Boolean isFullTimeFisherman;

	private String village;
	private String taluk;
	private String district;

	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIncomeDet() {
		return incomeDet;
	}

	public void setIncomeDet(String incomeDet) {
		this.incomeDet = incomeDet;
	}

	public Boolean getMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBiometricId() {
		return biometricId;
	}

	public void setBiometricId(String biometricId) {
		this.biometricId = biometricId;
	}

	public String getBiometricAppFormDetails() {
		return biometricAppFormDetails;
	}

	public void setBiometricAppFormDetails(String biometricAppFormDetails) {
		this.biometricAppFormDetails = biometricAppFormDetails;
	}

	public String getRationCardId() {
		return rationCardId;
	}

	public void setRationCardId(String rationCardId) {
		this.rationCardId = rationCardId;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getAdhaarNo() {
		return adhaarNo;
	}

	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}

	public String getNationalBankDetails() {
		return nationalBankDetails;
	}

	public void setNationalBankDetails(String nationalBankDetails) {
		this.nationalBankDetails = nationalBankDetails;
	}

	public String getGroupAccDetails() {
		return groupAccDetails;
	}

	public void setGroupAccDetails(String groupAccDetails) {
		this.groupAccDetails = groupAccDetails;
	}

	public String getCooperativeBankDetails() {
		return cooperativeBankDetails;
	}

	public void setCooperativeBankDetails(String cooperativeBankDetails) {
		this.cooperativeBankDetails = cooperativeBankDetails;
	}

	public String getTnFishAssociationDetails() {
		return tnFishAssociationDetails;
	}

	public void setTnFishAssociationDetails(String tnFishAssociationDetails) {
		this.tnFishAssociationDetails = tnFishAssociationDetails;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Boolean getIsPrevYearbenefitter() {
		return isPrevYearbenefitter;
	}

	public void setIsPrevYearbenefitter(Boolean isPrevYearbenefitter) {
		this.isPrevYearbenefitter = isPrevYearbenefitter;
	}

	public String getBenefittedYear() {
		return benefittedYear;
	}

	public void setBenefittedYear(String benefittedYear) {
		this.benefittedYear = benefittedYear;
	}

	public Boolean getIsBenefitter() {
		return isBenefitter;
	}

	public void setIsBenefitter(Boolean isBenefitter) {
		this.isBenefitter = isBenefitter;
	}

	public Boolean getIsGettingReliefFund() {
		return isGettingReliefFund;
	}

	public void setIsGettingReliefFund(Boolean isGettingReliefFund) {
		this.isGettingReliefFund = isGettingReliefFund;
	}

	public Boolean getIsFullTimeFisherman() {
		return isFullTimeFisherman;
	}

	public void setIsFullTimeFisherman(Boolean isFullTimeFisherman) {
		this.isFullTimeFisherman = isFullTimeFisherman;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTaluk() {
		return taluk;
	}

	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
