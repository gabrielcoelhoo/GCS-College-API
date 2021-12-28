package com.gabriel.gcscollegeAPI.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.gabriel.gcscollegeAPI.dto.StudentInputDTO;
import com.gabriel.gcscollegeAPI.model.Course;
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
	
	@Autowired
	private ModelMapper mapper;
	
	
	@GetMapping("/{studentID}")
	public Student findByID(@PathVariable Long studentID) {
		return studentService.findByIDOrThrowsException(studentID);
	}

	@PostMapping("submitStudent")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String submission(@RequestBody @Valid StudentInputDTO student) {
		
	studentService.saveStudent(toDomain(student));
		
		return "new student is added";
		
	}
	
	@PutMapping("/{studentID}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String update(@RequestBody @Valid StudentInputDTO student, @PathVariable  Long studentID) {
		Student studentBD = studentService.findByIDOrThrowsException(studentID);

		mapper.map(student, studentBD);
		
		studentService.updateStudent(studentBD);
		
		return "new student is added";
		
	}
	
	@PostMapping("userLogin")
	public Token login(@RequestBody Login login) {
		
		return studentService.login(login);
			
	}
	
	
	@PostMapping("submitCourse")
	public Course booking(@RequestBody Course course) {
		
		return studentService.saveCourse(course);
			
	}
	
	private Student toDomain(StudentInputDTO input) {
		
		return mapper.map(input, Student.class);
		
	}
	

}

