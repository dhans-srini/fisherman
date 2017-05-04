package com.revature.models;

public class Socity {
	private Long id;
	private String name;
	private String code;
	private Long villageId;
	private Boolean isMale;
	private Boolean isFemale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

	public Boolean getIsFemale() {
		return isFemale;
	}

	public void setIsFemale(Boolean isFemale) {
		this.isFemale = isFemale;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null && this.hashCode() == obj.hashCode()) {
			result = true;
		}
		return result;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}