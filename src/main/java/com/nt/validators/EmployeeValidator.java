package com.nt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nt.model.Employee;

@Component("empValidator")
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Employee.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("EmployeeValidator.validate()");
		Employee emp=null;
		emp=(Employee) target;
		if(emp.getEmpName().isEmpty()||emp.getEmpName()==null) {
			errors.rejectValue("empName", "emp.ename.required");
		}
		else if(emp.getEmpName().length()<5||emp.getEmpName().length()>10) {
			errors.rejectValue("empName", "emp.ename.length");
		}
		if(emp.getSal()==null) {
			errors.rejectValue("sal", "emp.sal.required");
		}
		else if(emp.getSal()<10000 || emp.getSal()>1000000) {
			errors.rejectValue("sal", "emp.sal.range");
		}
		if(emp.getJob().isEmpty()||emp.getJob()==null) {
			errors.rejectValue("job", "emp.job.required");
		}
		else if(emp.getJob().length()<5||emp.getJob().length()>9) {
			errors.rejectValue("job", "emp.job.length");
		}

	}

}
