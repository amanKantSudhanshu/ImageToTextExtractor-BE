package com.vitraya.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vitraya.entity.Employee;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	Optional<Employee> findOneByEmailAndPassword(String email, String encodedPassword);

	Employee findByEmail(String email);
	
	
	

}
