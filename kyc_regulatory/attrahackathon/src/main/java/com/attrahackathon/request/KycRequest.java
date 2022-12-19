package com.attrahackathon.request;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kyc_request")
public class KycRequest {
	
	
	private int id;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String dateOfBirth;
	
	private List<IdType> idType;
	
	private String email;
	
	private String mobile;
	
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	
	private String notes;
	
	@Id
	private String primaryIdNumber;
	
	private String primaryIdType;
	
	/**
	 * APPROVED/REJECTED/PENDING
	 */
	private String status;
	
	/**
	 * USER/BANK/REGBODY
	 */
	private String source;
	
	/**
	 * It will be bank, Reg Body or User itself.
	 */
	private String initiatedBy;
	
	private String uniqueHexCode;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<IdType> getIdType() {
		return idType;
	}
	public void setIdType(List<IdType> idType) {
		this.idType = idType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getInitiatedBy() {
		return initiatedBy;
	}
	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUniqueHexCode() {
		return uniqueHexCode;
	}
	public void setUniqueHexCode(String uniqueHexCode) {
		this.uniqueHexCode = uniqueHexCode;
	}
	
	public String getPrimaryIdNumber() {
		return primaryIdNumber;
	}
	public void setPrimaryIdNumber(String primaryIdNumber) {
		this.primaryIdNumber = primaryIdNumber;
	}
	public String getPrimaryIdType() {
		return primaryIdType;
	}
	public void setPrimaryIdType(String primaryIdType) {
		this.primaryIdType = primaryIdType;
	}
	@Override
	public String toString() {
		return "KycRequest [firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", idType=" + idType + ", email=" + email
				+ ", mobile=" + mobile + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zipCode="
				+ zipCode + "]";
	}
	
}
