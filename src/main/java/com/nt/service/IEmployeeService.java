package com.nt.service;

import java.util.List;

import com.nt.dto.EmployeeDTO;

public interface IEmployeeService {
	List<EmployeeDTO> fetchEmpDTO() throws Exception;
	String registerEmp(EmployeeDTO dto) throws Exception;
	List<Integer> getDeptnos();
	void removeEmployeeById(int id);
	EmployeeDTO fetchEmployeeById(int id);
	String alterEmployeeById(EmployeeDTO dto);
}
