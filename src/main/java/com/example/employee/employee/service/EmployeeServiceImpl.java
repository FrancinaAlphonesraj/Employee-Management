package com.example.employee.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.employee.entity.Employee;
import com.example.employee.employee.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeerepo;

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeerepo.save(employee);
	}

	@Override
	public List<Employee> fetchEmployeeList() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeerepo.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee, Long empId) {
		// TODO Auto-generated method stub

		Employee emp = employeerepo.findById(empId).get();

		emp.setEmpId(employee.getEmpId());
		emp.setEmployeeName(employee.getEmployeeName());
		emp.setEmployeeAge(employee.getEmployeeAge());
		emp.setHiredate(employee.getHiredate());
		emp.setPhoneno(employee.getPhoneno());
		emp.setEmployeeDesignation(employee.getEmployeeDesignation());
		emp.setEmployeeSalary(employee.getEmployeeSalary());

		return employeerepo.save(emp);
	}

	@Override
	public void deleteEmployeebyID(Long empId) {

		employeerepo.deleteById(empId);
	}

	@Override
	public Optional<Employee> findById(long empId) {

		return employeerepo.findById(empId);

	}

}