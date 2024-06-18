package com.vitraya.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitraya.dto.EmployeeDto;
import com.vitraya.dto.LoginDto;
import com.vitraya.entity.Employee;
import com.vitraya.managementResponse.LoginResponse;
import com.vitraya.repository.EmployeeRepository;
import com.vitraya.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public String addEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setEmail(employeeDto.getEmail());
		employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

		employeeRepository.save(employee);
        return employee.getEmployeeName();
	}

	@Override
	public LoginResponse loginEmployee(LoginDto loginDTO) {
		String msg = "";
		Employee employeeFound = employeeRepository.findByEmail(loginDTO.getEmail());
		if (employeeFound != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = employeeFound.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if (isPwdRight) {
				Optional<Employee> employee = employeeRepository.findOneByEmailAndPassword(loginDTO.getEmail(),
						encodedPassword);
				if (employee.isPresent()) {
					return new LoginResponse("Login Successful", true);
				} else {
					return new LoginResponse("Login Failed", false);
				}
			} else {
				return new LoginResponse("Password Not Match", false);
			}
		} else {
			return new LoginResponse("Email not exits", false);
		}
	}

	@Override
	public List<Employee> findAllEmployee() {
		List<Employee> allEmployee = (List<Employee>) employeeRepository.findAll();
		return allEmployee;
	}

}
