package com.ibcs.ibcs.service;

import java.util.List;

import com.ibcs.ibcs.domain.Department;





public interface DepartmentService {
	List<Department> findAll ();
	
	Department findOne(Long id);
	
	Department save(Department department);

	void removeOne(Long id);
}
