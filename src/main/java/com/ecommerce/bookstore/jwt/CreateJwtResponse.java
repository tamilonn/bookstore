package com.ecommerce.bookstore.jwt;

public class CreateJwtResponse {

	private String token;
	private String error;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}