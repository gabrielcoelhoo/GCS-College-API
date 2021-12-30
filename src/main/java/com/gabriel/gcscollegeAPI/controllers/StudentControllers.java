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
import com.gabriel.gcscollegeAPI.services.StudentServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/students")
public class StudentControllers {

	@Autowired
	private StudentServiceImpl studentService;
	
	
//answer back to the front if the id is not found
	@GetMapping("/{studentID}")
	public Student findByID(@PathVariable Long studentID) {
		return studentService.findByIDOrThrowsException(studentID);
	}
	
	@GetMapping("/all")
	public List<Student> findAllCourses() {
		return studentService.findAll();
	}

	@PostMapping("/submitStudent")
	public String submission(@RequestBody Student student) {

		studentService.saveStudent(student);
		return "this student has been created successfully";

	}

	@PutMapping("/{studentID}")
	@ResponseStatus(value = HttpStatus.OK)
	public String update(@RequestBody @Valid Student student, @PathVariable Long studentID) {
		Student studentBD = studentService.findByIDOrThrowsException(studentID);

		studentService.updateStudent(studentBD);

		return "this student has been updated successfully";

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
