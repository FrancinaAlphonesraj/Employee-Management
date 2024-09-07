package com.example.employee.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.employee.entity.Department;
import com.example.employee.employee.repo.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepo deptrepo;

	@Override
	public Department saveDepartment(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.save(dept);
	}

	@Override
	public List<Department> fetchDepartmentList() {

		return (List<Department>) deptrepo.findAll();
	}

	@Override
	public Optional<Department> findByDeptId(Long deptId) {

		return deptrepo.findById(deptId);
	}

	@Override
	public Department updateDepartment(Department dept, Long deptId) {

		Department department = deptrepo.findById(deptId).get();

		if (department.getDepartmentName() != null) {

			department.setDepartmentName(department.getDepartmentName());

		}

		return deptrepo.save(department);
	}

	@Override
	public void deleteDepartmentbyID(Long deptId) {
		// TODO Auto-generated method stub
		deptrepo.deleteById(deptId);

	}

}
