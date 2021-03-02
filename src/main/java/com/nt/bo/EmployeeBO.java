package com.nt.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "EMP")
public class EmployeeBO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPNO")
	private Integer empNo;
	 @Column(name="ENAME")
	private String empName;
	 @Column(name="DEPTNO")
	private Integer deptNo;
	private Float sal;
	private String job;

}
