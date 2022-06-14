package com.ecommerce.bookstore.jwt;

import java.util.HashMap;
import java.util.Map;

public class VerifyJwtResponse {

	private boolean status = false;
	private Map<String, String> payload = new HashMap<String, String>();
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Map<String, String> getPayload() {
		return payload;
	}
	public void setPayload(Map<String, String> payload) {
		this.payload = payload;
	}
	
	

}