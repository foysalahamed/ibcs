package com.ibcs.ibcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibcs.ibcs.domain.Department;
import com.ibcs.ibcs.repository.DepartmentRepository;
import com.ibcs.ibcs.service.DepartmentService;



@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Department save(Department department) {
		return departmentRepository.save(department);
	}
	
	public List<Department> findAll() {
		return (List<Department>) departmentRepository.findAll();
	}
	
	public Department findOne(Long id) {
		return departmentRepository.findOne(id);
	}
	
	public void removeOne(Long id) {
		departmentRepository.delete(id);
	}
}
