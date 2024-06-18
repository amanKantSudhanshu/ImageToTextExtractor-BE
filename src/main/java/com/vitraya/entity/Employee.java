package com.vitraya.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="employee")
@Data
public class Employee {
  
	@Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    
    @Column(name="employee_name", length = 255, nullable = false)
    private String employeeName;
    
    @Column(name="phone_num", length = 10)
    private String phoneNumber;
    
    @Column(name="email", length = 255,nullable = false, unique = true)
    private String email;
    
    @Column(name="password", length = 255)
    private String password;

}
