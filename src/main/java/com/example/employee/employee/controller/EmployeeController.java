package com.example.employee.employee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.employee.employee.entity.Department;
import com.example.employee.employee.entity.Employee;
import com.example.employee.employee.repo.DepartmentRepo;
import com.example.employee.employee.repo.EmployeeRepo;
import com.example.employee.employee.service.DepartmentService;
import com.example.employee.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")

public class EmployeeController {
	
	@Autowired
	private EmployeeService empservice;
	
	@Autowired
	private DepartmentService deptservice;
	
	
	@GetMapping("/**")
    public String viewHomePage(Model model) {
        return "index";
    }
	
	    
	    @ApiOperation(value = "View a list of available employees", response = List.class)
		@GetMapping("/showEmployeeList")
	    public String employeeList(Model model) {
	        model.addAttribute("employees", empservice.fetchEmployeeList());
	        return "EmployeeList";
	    }
	    
	    @GetMapping("/showNewEmployeeForm")
	    public String showNewEmployeeForm(Model model) {
	        model.addAttribute("employee", new Employee());
	        model.addAttribute("departments", deptservice.fetchDepartmentList());

	        return "Employee";
	    }
	

	
	@ApiOperation(value = "Add an employee")
	@PostMapping("/create")
    public String saveEmployee( @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		
		 Department department = deptservice.findByDeptId(employee.getDepartment().getDeptId())
                 .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));	    
		 employee.setDepartment(department);

		empservice.saveEmployee(employee);
        return "redirect:/showEmployeeList";
    }
	
	
	
	@ApiOperation(value = "Get an employee by Id")
	@GetMapping("/employee/update/{empId}")
    public String updateEmployee(@PathVariable("empId") Long empId, Model model) {
        
//		Employee emp = empservice.findById(empId).orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id:" +empId));
//		emp.setEmpId(empId);
//		Department department = deptservice.findByDeptId(emp.getDepartment().getDeptId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));	    
//		 emp.setDepartment(department);

//		List<Department> departments = deptservice.fetchDepartmentList();
        model.addAttribute("employee", empservice.findById(empId));
        model.addAttribute("departments", deptservice.fetchDepartmentList());

        return "Employee_Update";
    }
	
//	@PostMapping("/update/{id}")
//	public String update(@PathVariable Long empId, @ModelAttribute Employee employee) {
//		employee.setEmpId(empId);
//		Department department = deptservice.findByDeptId(employee.getDepartment().getDeptId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));	    
//		 employee.setDepartment(department);
//		empservice.saveEmployee(employee);
//		return "redirect:/showEmployeeList";
//		
//	}

    
	@ApiOperation(value = "Delete an employee")
	@GetMapping("/deleteEmployee/{empId}")
    public String deleteEmployee(@PathVariable(value = "empId") long empId) {
		Employee emp = empservice.findById(empId).orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id:" +empId));
        empservice.deleteEmployeebyID(empId);
        return "redirect:/showEmployeeList";
    }
	
	
	

}
