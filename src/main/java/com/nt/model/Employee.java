package com.nt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Employee {
	private Integer empNo;
	private String empName;
	private Integer deptNo;
	private Float sal;
	private String job;
	private String vflag="no";
}
