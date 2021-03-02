package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.dto.EmployeeDTO;
import com.nt.model.Employee;
import com.nt.service.IEmployeeService;
import com.nt.validators.EmployeeValidator;

@Controller
@RequestMapping("/employee")
public class ShowEmployeeController {
	@Autowired
	private IEmployeeService service;
	@Autowired
	private EmployeeValidator empValidator;
	
	@RequestMapping("/welcome.htm")
    public String showHome(Model model) {	
		return"home";
	}
	
	@RequestMapping("/employee.htm")
	public String showEmployees(Map<String, Object> map) {
		
		List<EmployeeDTO> listDTO=null;
		try {
			listDTO=service.fetchEmpDTO();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		map.put("listInfo",listDTO);
		return"result";
	}
	
	@GetMapping("/registerEmp.htm")
	public String showRegisterForm(@ModelAttribute("emp") Employee employee ) {
		return "register";
	}
	
	@PostMapping("/registerEmp.htm")
	public String processRegisterForm(RedirectAttributes redirect,@ModelAttribute("emp") Employee employee,BindingResult errors) {
		EmployeeDTO dto=null;
		String msg=null;
		List<EmployeeDTO> listDTO=null;
		System.out.println("validation "+employee.getVflag());
		if(employee.getVflag().equalsIgnoreCase("no")) {
			if(empValidator.supports(employee.getClass())){
				empValidator.validate(employee, errors);
			}
		}
//		if(empValidator.supports(employee.getClass())){
//			empValidator.validate(employee, errors);
//		}
		if(employee.getJob().equalsIgnoreCase("netaji")||employee.getJob().equalsIgnoreCase("actor")) {
			errors.rejectValue("job","blocked.jobs");
		}
		
		if(errors.hasErrors()) {
			return "register";
		}
		dto=new EmployeeDTO();
		BeanUtils.copyProperties(employee, dto);
		try {
			msg=service.registerEmp(dto);
			listDTO=service.fetchEmpDTO();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirect.addFlashAttribute("registerAtt",msg);   
		return "redirect:employee.htm";
	
	}
	
	@ModelAttribute("deptAtt")
	public List<Integer> populateDeptNo(){
		return service.getDeptnos();
	}
	
	@GetMapping("/deleteEmp.htm")
	public String deleteEmployee(RedirectAttributes redirect, @RequestParam int eno) {
		String msg=null;
		service.removeEmployeeById(eno);
		msg="employee with "+eno+" is deleted";
		redirect.addFlashAttribute("deleteAtt",msg);
		return "redirect:employee.htm";
	}
	
	@GetMapping("/editEmp.htm")
	public String showEditForm(@RequestParam int eno,@ModelAttribute("emp") Employee employee) {
		EmployeeDTO dto=null;
		dto=service.fetchEmployeeById(eno);
		System.out.println(dto);
		BeanUtils.copyProperties(dto, employee);
		return "edit";
	}
	
	@PostMapping("/editEmp.htm")
	public String processEditForm(RedirectAttributes redirect,@ModelAttribute("emp") Employee employee,BindingResult errors) {
		EmployeeDTO dto=null;
		String msg=null;
		if(empValidator.supports(employee.getClass())){
			empValidator.validate(employee, errors);
		}
		if(errors.hasErrors()) {
			return "edit";
		}
		dto=new EmployeeDTO();
		BeanUtils.copyProperties(employee, dto);
		msg=service.alterEmployeeById(dto);
		redirect.addFlashAttribute("editAtt",msg);
		return "redirect:employee.htm";
	}
	

}
