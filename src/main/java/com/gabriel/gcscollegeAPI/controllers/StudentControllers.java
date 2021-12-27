package com.gabriel.gcscollegeAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.services.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class StudentControllers {
	
	@Autowired
	private StudentService studentService;

	@PostMapping("submitStudent")
	public String submission(@RequestBody Student student) {
		
		studentService.saveStudent(student);
		
		return "new student is added";
		
	}
	
	@PostMapping("userLogin")
	public Token login(@RequestBody Login login) {
		
		return studentService.login(login);
			
	}
	
	
//	@PostMapping("submitCourse")
//	public String booking(@RequestBody Course course) {
//		
//		 studentService.saveCourse(course);
//		 
//		 return "course has beed added";
//		
//		
//	}
	

}

