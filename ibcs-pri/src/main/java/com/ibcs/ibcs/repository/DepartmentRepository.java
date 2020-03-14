package com.ibcs.ibcs.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibcs.ibcs.domain.Department;




public interface DepartmentRepository extends CrudRepository<Department, Long>{
	
}
