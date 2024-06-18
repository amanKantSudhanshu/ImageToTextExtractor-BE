package com.vitraya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitraya.dto.EmployeeDto;
import com.vitraya.dto.LoginDto;
import com.vitraya.entity.Employee;
import com.vitraya.exception.AlreadyExistException;
import com.vitraya.managementResponse.LoginResponse;
import com.vitraya.services.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@PostMapping(value = "/saveEmployee")
	public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		String employeeName = empService.addEmployee(employeeDto);
		return ResponseEntity.ok(employeeName.toString());
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDTO) {
		LoginResponse loginResponse = empService.loginEmployee(loginDTO);
		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping(value = "/getAllEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> findAllEmployee = empService.findAllEmployee();
		return new ResponseEntity<>(findAllEmployee, HttpStatus.OK);
	}
}
