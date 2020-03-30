package com.ibcs.ibcs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.ibcs.ibcs.domain.Department;
import com.ibcs.ibcs.domain.Employee;
import com.ibcs.ibcs.domain.Gender;
import com.ibcs.ibcs.service.DepartmentService;
import com.ibcs.ibcs.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		Department department = new Department();
		model.addAttribute("department", department);
		model.addAttribute("genderstype", Gender.values());
		model.addAttribute("departmentList", departmentService.findAll());

		return "employee";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addEmployeePost(@Valid Employee employee, BindingResult bindingResult,
			@ModelAttribute("birthdate") String birthdate, @ModelAttribute("joiningdate") String joiningdate

	) {

		if (bindingResult.hasErrors()) {

			return "employee";
//			return "redirect:employee/add";
		} else {

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");

			try {
				Date birthDate;
				birthDate = format1.parse(birthdate);
				employee.setDateOfBirth(birthDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Date joinDate;
				joinDate = format1.parse(joiningdate);
				employee.setJoiningDate(joinDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			employeeService.save(employee);
			return "redirect:employeeList";
		}

	}

	@RequestMapping("/updateEmployee")
	public String updateEmployee(@RequestParam("id") Long id, Model model) {
		Employee employee = employeeService.findOne(id);
		model.addAttribute("employee", employee);
		List<Department> departmentList = departmentService.findAll();
		model.addAttribute("departmentList", departmentList);
		return "employee";
	}

	@RequestMapping("/employeeList")
	public String employeeList(Model model) {
		List<Employee> employeeList = employeeService.findAll();
		model.addAttribute("employeeList", employeeList);
		return "all_employees";

	}

//	@RequestMapping(value="/remove", method={ RequestMethod.GET})
//	public String remove(@RequestParam("id") Long id, Model model) {
//		employeeService.removeOne(id);
//		List<Employee> employeeList = employeeService.findAll();
//		model.addAttribute("employeeList", employeeList);
//		
//		return "all_employees";
//
//	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("id") Long id, Model model) {

		employeeService.removeOne(id);
		List<Employee> employeeList = employeeService.findAll();
		model.addAttribute("employeeList", employeeList);

		return "redirect:/employee/employeeList";
	}

	@RequestMapping("/report")
	public ModelAndView employeeRport(Model model) {

		List<Employee> employeeList = employeeService.findAll();
//		model.addAttribute("employeeList", employeeList);

		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/employeeReport.jrxml");

		view.setApplicationContext(applicationContext);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datasource", employeeService.report(employeeList));
		ModelAndView mv = new ModelAndView(view, params);
		return mv;

	}

}