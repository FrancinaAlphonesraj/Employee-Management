package com.example.employee.employee.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee")
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	@Column(name = "empname", nullable = false)
	private String employeeName;

	@Column(name = "empage", nullable = false)
	private int employeeAge;

	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hiredate;

	@Column(name = "phoneno")
	private String phoneno;

	@Column(name = "empdesignation", nullable = false)
	private String employeeDesignation;

	@Column(name = "empsalary", nullable = false)
	private double employeeSalary;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "deptId", nullable = false)
	private Department department;

}
