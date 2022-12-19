package com.attrahackathon.request;

public class IdType {
	
	private String idNumber;
	
	private String type;
	
	private String description;

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "IdType [idNumber=" + idNumber + ", type=" + type
				+ ", description=" + description + "]";
	}
	
}
