package com.gabriel.gcscollegeAPI.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Employee;
import com.gabriel.gcscollegeAPI.repositories.EmployeeRepository;
import com.gabriel.gcscollegeAPI.services.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/employees")
public class EmployeeController {
		
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@GetMapping("/getById/{employeeId}")
	public Employee findByID(@PathVariable Long employeeId) {
		return employeeService.findOrThrowsException(employeeId);
	}
	
	@GetMapping("/all")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String submission(@RequestBody Employee employee) {

		employeeService.save(employee);
		
		return "this employee has been created successfully";	
	}
	
	@PutMapping("/update/{employeeID}")
	@ResponseStatus(value = HttpStatus.OK)
	public Employee update(@RequestBody @Valid Employee repEmployee, @PathVariable Long employeeID) {
		Employee employeeBD = employeeService.findOrThrowsException(employeeID);

		return employeeRepository.findById(employeeID)
			      .map(employee -> {
			    	  employee.setName(repEmployee.getName());
			        return employeeRepository.save(employee);
			      })
			      .orElseGet(() -> {
			    	  repEmployee.setId(employeeID);
			        return employeeRepository.save(repEmployee);
			      });
		
		//map function taken from 
		//https://spring.io/guides/tutorials/rest/

	}
	
	@DeleteMapping("/delete/{employeeID}")
	@ResponseStatus(value = HttpStatus.OK)
	public String delete(@PathVariable Long employeeID) {
		employeeService.delete(employeeID);
		return "this employee has been deleted successfully";

	}
	
//	@PostMapping("userLogin")
//	public Token login(@RequestBody Login login) {
//		
//		return employeeService.login(login);
//			
//	}
	

}
