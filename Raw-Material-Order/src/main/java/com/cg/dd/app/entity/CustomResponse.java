package com.cg.dd.app.entity;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
public class CustomResponse {
	
	public int responseCode;
	public String responseMessage;
	
	public CustomResponse(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
		
		
}
