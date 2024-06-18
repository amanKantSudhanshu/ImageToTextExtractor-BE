package com.vitraya.managementResponse;

import lombok.Data;

@Data
public class LoginResponse {
    String message;
    Boolean status;
    
    public LoginResponse(String message, Boolean status) {
    	this.message = message;
    	this.status = status;
    }
}