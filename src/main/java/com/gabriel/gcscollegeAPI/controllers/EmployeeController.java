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
import com.gabriel.gcscollegeAPI.services.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/employees")
public class EmployeeController {
		
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@GetMapping("/{employeeId}")
	public Employee findByID(@PathVariable Long employeeId) {
		return employeeService.findOrThrowsException(employeeId);
	}
	
	@GetMapping
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Employee submission(@RequestBody Employee employe) {

		employeeService.save(employe);

		return employe;
	}
	
	@PutMapping("/{employeeID}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String update(@RequestBody @Valid Employee employee, @PathVariable Long employeeID) {
		employeeService.findOrThrowsException(employeeID);

		employeeService.update(employee);

		return "new student is added";

	}
	
	@DeleteMapping("/{employeeID}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String delete(@PathVariable Long employeeID) {
		employeeService.delete(employeeID);
		return "new student is added";

	}
	
//	@PostMapping("userLogin")
//	public Token login(@RequestBody Login login) {
//		
//		return employeeService.login(login);
//			
//	}
	

}

