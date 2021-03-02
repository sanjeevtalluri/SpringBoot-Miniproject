package com.nt.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmployeeDTO {
	private Integer serialNo;
	private Integer empNo;
	private String empName;
	private Integer deptNo;
	private Float sal;
	private String job;
}
