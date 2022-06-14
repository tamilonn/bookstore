package com.ecommerce.bookstore.jwt;

import java.util.HashMap;
import java.util.Map;

public class CreateJwtRequest {
	
	private Map<String, String> payload = new HashMap<String, String>();
    private String subject;
    
	public Map<String, String> getPayload() {
		return payload;
	}
	public void setPayload(Map<String, String> payload) {
		this.payload = payload;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
    
    

}
