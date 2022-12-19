package com.attrahackathon.request;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kyc_mapping")
public class KycMapping {
	
	@Id
	private String uniqueHex;
	private BigInteger blockNumber;
	private String uniqueHexCode;
	
	public String getUniqueHex() {
		return uniqueHex;
	}
	public void setUniqueHex(String uniqueHex) {
		this.uniqueHex = uniqueHex;
	}
	public BigInteger getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(BigInteger blockNumber) {
		this.blockNumber = blockNumber;
	}
	public String getUniqueHexCode() {
		return uniqueHexCode;
	}
	public void setUniqueHexCode(String uniqueHexCode) {
		this.uniqueHexCode = uniqueHexCode;
	}
	
	
}
