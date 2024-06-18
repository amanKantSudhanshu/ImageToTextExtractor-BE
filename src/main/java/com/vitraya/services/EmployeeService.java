package com.vitraya.services;

import java.util.List;

import com.vitraya.dto.EmployeeDto;
import com.vitraya.dto.LoginDto;
import com.vitraya.entity.Employee;
import com.vitraya.exception.AlreadyExistException;
import com.vitraya.managementResponse.LoginResponse;

public interface EmployeeService {
	
	public String addEmployee(EmployeeDto employeeDto);
	public LoginResponse loginEmployee(LoginDto loginDTO);
	public List<Employee> findAllEmployee();

}
