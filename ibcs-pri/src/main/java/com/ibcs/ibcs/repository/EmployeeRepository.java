package com.ibcs.ibcs.repository;


import org.springframework.data.repository.CrudRepository;

import com.ibcs.ibcs.domain.Employee;




public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
}
