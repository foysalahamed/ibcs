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

public class HomeController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String addDepartment() {
		
		return "home";
	}

	
}
