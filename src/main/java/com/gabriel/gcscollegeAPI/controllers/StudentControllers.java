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

import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;
import com.gabriel.gcscollegeAPI.services.StudentServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/students")
public class StudentControllers {

	@Autowired
	private StudentServiceImpl studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
//answer back to the front if the id is not found
	@GetMapping("/{studentID}")
	public Student findByID(@PathVariable Long studentID) {
		return studentService.findByIDOrThrowsException(studentID);
	}
	
	@GetMapping("/all")
	public List<Student> findAllCourses() {
		return studentService.findAll();
	}

	@PostMapping("/create")
	public String submission(@RequestBody Student student) {

		studentService.saveStudent(student);
		return "this student has been created successfully";

	}

	@PutMapping("/update/{studentID}")
	@ResponseStatus(value = HttpStatus.OK)
	public Student update(@RequestBody @Valid Student repStudent, @PathVariable Long studentID) {
		Student studentBD = studentService.findByIDOrThrowsException(studentID);

		return studentRepository.findById(studentID)
			      .map(student -> {
			    	  student.setAddress(repStudent.getAddress());
			    	  student.setCountry(repStudent.getCountry());
			    	  student.setEmail(repStudent.getEmail());
			    	  student.setName(repStudent.getName());
			    	  student.setPassword(repStudent.getPassword());
			    	  student.setPhoneNumber(repStudent.getPhoneNumber());
			    	  student.setStudentComments(repStudent.getStudentComments());
			    	  student.setSurname(repStudent.getSurname());
			        return studentRepository.save(student);
			      })
			      .orElseGet(() -> {
			    	  repStudent.setId(studentID);
			        return studentRepository.save(repStudent);
			      });	
		
		//method taken from 
		//https://spring.io/guides/tutorials/rest/

	}

	@PostMapping("/userLogin")
	public Token login(@RequestBody Login login) {

		return studentService.login(login);

	}
	
	@DeleteMapping("/{idCourse}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteProduct(@PathVariable Long idCourse) {
		studentService.deleteStudant(idCourse);
		return "this course has been deleted successfully";

	}


}
