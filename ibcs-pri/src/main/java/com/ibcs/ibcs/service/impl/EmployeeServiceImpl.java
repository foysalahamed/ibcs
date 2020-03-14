package com.ibcs.ibcs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


import com.ibcs.ibcs.domain.Employee;
import com.ibcs.ibcs.repository.EmployeeRepository;
import com.ibcs.ibcs.service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
	@Autowired
	private EmployeeRepository employeetRepository;
	
	public Employee save(Employee employeet) {
		
		return employeetRepository.save(employeet);
	}
	
	public List<Employee> findAll() {
		return (List<Employee>) employeetRepository.findAll();
	}
	
	public Employee findOne(Long id) {
		return employeetRepository.findOne(id);
	}
	
	public void removeOne(Long id) {
		employeetRepository.delete(id);
	}
	public List<Map<String, Object>> report(List<Employee> employeeList) {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (Employee employee : employeeList) {
			System.out.println("emp name:"+employee.getName());
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", employee.getId());
			item.put("name", employee.getName());
			item.put("joiningdate", employee.getJoiningDate());
			item.put("birthdate", employee.getDateOfBirth());
			item.put("designation", employee.getDesignation());
			item.put("basic", employee.getBasic());
			item.put("gender", employee.getGender());
			item.put("department", employee.getDepartment().getName());


			result.add(item);
		}
		return result;

	}

}
