package com.revature.models;

public class Village {
	private Long id;
	private String name;

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