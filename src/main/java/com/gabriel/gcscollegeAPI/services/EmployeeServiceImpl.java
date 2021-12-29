package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Employee;
import com.gabriel.gcscollegeAPI.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Transactional
	public void delete(Long id) {
		Employee found = findOrThrowsException(id);
		employeeRepository.delete(found);
	}

	@Transactional
	public Employee update(Employee employee) {
		return save(employee);
	}

	public Employee findOrThrowsException(Long id) {
		return employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The employee of id %d was not found!", id)));
	}

}
