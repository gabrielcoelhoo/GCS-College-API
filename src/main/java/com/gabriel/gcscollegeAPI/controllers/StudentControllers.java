package com.gabriel.gcscollegeAPI.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.services.StudentServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/students")
public class StudentControllers {

	@Autowired
	private StudentServiceImpl studentService;

	@GetMapping("/{studentID}")
	public Student findByID(@PathVariable Long studentID) {
		return studentService.findByIDOrThrowsException(studentID);
	}

	@PostMapping("submitStudent")
	public Student submission(@RequestBody Student student) {

//	    String encodedPassword;
//	    
//	    //encode the password
//	    encodedPassword = encoder.encode(student.getPassword());
//	    
//	    Set<Role> securityRoles = new HashSet<Role>();
//
//	    //get Role
//	    securityRoles.add(roleDao.findById(userForm.getRoleId()));
//
//	    user.setRoles(securityRoles);
//
//	    user.setTenantId(securityAccessor.getCurrentLoggedUser().getTenantId());

		studentService.saveStudent(student);

		return student;

	}

	@PutMapping("/{studentID}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String update(@RequestBody @Valid Student student, @PathVariable Long studentID) {
		Student studentBD = studentService.findByIDOrThrowsException(studentID);

		studentService.updateStudent(studentBD);

		return "new student is added";

	}

	@PostMapping("userLogin")
	public Token login(@RequestBody Login login) {

		return studentService.login(login);

	}

}
