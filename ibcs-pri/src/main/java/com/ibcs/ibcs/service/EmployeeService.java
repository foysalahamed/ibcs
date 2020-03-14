package com.ibcs.ibcs.service;

import java.util.List;
import java.util.Map;

import com.ibcs.ibcs.domain.Employee;





public interface EmployeeService {
	List<Employee> findAll ();
	
	Employee findOne(Long id);
	
	Employee save(Employee employee);

	void removeOne(Long id);
	public List<Map<String, Object>> report(List<Employee> employeeList);
}
