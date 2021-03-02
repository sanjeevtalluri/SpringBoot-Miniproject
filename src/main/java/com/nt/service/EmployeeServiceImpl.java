package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import com.nt.bo.EmployeeBO;

import com.nt.dto.EmployeeDTO;
import com.nt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public List<EmployeeDTO> fetchEmpDTO() throws Exception {
		List<EmployeeBO> listBO=null;
		List<EmployeeDTO> listDTO=new ArrayList<EmployeeDTO>();
		listBO=(List<EmployeeBO>) repository.findAll();
		System.out.println("EmployeeServiceImpl.fetchEmpDTO() "+ listBO.get(0).getEmpName());
		listBO.forEach(bo->{
			EmployeeDTO dto=new EmployeeDTO();
			BeanUtils.copyProperties(bo, dto);
			dto.setSerialNo(listDTO.size()+1);
			listDTO.add(dto);
		});
		return listDTO;
	}
	@Override
	public String registerEmp(EmployeeDTO dto) throws Exception {
		EmployeeBO bo=null;
		bo=new EmployeeBO();
		BeanUtils.copyProperties(dto, bo);
		bo=repository.save(bo);
		return bo==null?"Employee registration is unsuccessful":"Employee registration is successful";
	}
	@Override
	public List<Integer> getDeptnos() {
		
		return repository.getAllDeptnos();
	}
	@Override
	public void removeEmployeeById(int id) {
		
		repository.deleteById(id);
		
	}
	@Override
	public EmployeeDTO fetchEmployeeById(int id) {
		EmployeeDTO dto=null;
		EmployeeBO bo=null;
		dto=new EmployeeDTO();
		bo=repository.findById(id).get();
		System.out.println(bo);
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}
	@Override
	public String alterEmployeeById(EmployeeDTO dto) {
		EmployeeBO bo=null;
		bo=new EmployeeBO();
		int count=0;
		BeanUtils.copyProperties(dto, bo);
		bo=repository.save(bo);
		return bo==null?"No employee found to be updated":"employee found and updated";
	}

}
