package com.ibcs.ibcs.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.ibcs.ibcs.domain.Department;
import com.ibcs.ibcs.service.DepartmentService;




@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addDepartment(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "department";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDepartmentPost(@Valid Department department,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "department";
		}
		else
			departmentService.save(department);

		return "redirect:departmentList";
	}

	@RequestMapping("/updateDepartment")
	public String updateDepartment(@RequestParam("id") Long id, Model model) {
		Department department = departmentService.findOne(id);
		model.addAttribute("department", department);
		
		return "department";
	}
	
	
	
	@RequestMapping("/departmentList")
	public String departmentList(Model model) {
		List<Department> departmentList = departmentService.findAll();
		model.addAttribute("departmentList", departmentList);		
		return "all_departments";
		
	}
	
	@RequestMapping(value="/remove", method={ RequestMethod.GET})
	public String remove(@RequestParam("id") Long id, Model model) {
		departmentService.removeOne(id);
		List<Department> departmentList = departmentService.findAll();
		model.addAttribute("departmentList", departmentList);
		
		return "all_departments";

	}

}
