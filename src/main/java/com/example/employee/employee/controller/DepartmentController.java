package com.example.employee.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employee.employee.entity.Department;
import com.example.employee.employee.entity.Employee;
import com.example.employee.employee.service.DepartmentService;
import com.example.employee.employee.service.EmployeeService;

import io.swagger.annotations.Api;

@Controller

public class DepartmentController {

	@Autowired
	private DepartmentService deptservice;

	@GetMapping("/showDepartmentList")
	public String showDeptLit(Model model) {
		model.addAttribute("departments", deptservice.fetchDepartmentList());
		return "DepartmentList";
	}

	@GetMapping("/showNewDepartmentForm")
	public String showNewDepartmentForm(Model model) {
		Department dept = new Department();
		model.addAttribute("department", dept);

		return "Department";
	}

	@PostMapping("/createDept")
	public String saveDepartment(@ModelAttribute("department") Department dept) {

		Department createdDept = deptservice.saveDepartment(dept);
		return "redirect:/showDepartmentList";
	}

	@GetMapping("/findDeptById/{deptId}")
	public String updateDepartment(@PathVariable(value = "deptId") long deptId, Model model) {
		Department department = deptservice.findByDeptId(deptId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Department Id:" + deptId));

		model.addAttribute("department", department);

		return "Department_Update";
	}

	@GetMapping("/deleteDept/{deptId}")
	public String deleteDepartment(@PathVariable(value = "deptId") long deptId) {
		Department dept = deptservice.findByDeptId(deptId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Department Id:" + deptId));
		deptservice.deleteDepartmentbyID(deptId);
		return "redirect:/showDepartmentList";
	}

}
