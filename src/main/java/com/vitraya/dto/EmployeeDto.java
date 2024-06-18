package com.vitraya.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	
	private Long employeeId;
    private String employeeName;
    private String email;
    private String password;
    private String phoneNumber;

}
