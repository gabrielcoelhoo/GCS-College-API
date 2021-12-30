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
import com.gabriel.gcscollegeAPI.model.Employee;
import com.gabriel.gcscollegeAPI.services.CourseServiceImpl;
import com.gabriel.gcscollegeAPI.services.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/courses")
public class CourseControllers {
	
	//all methods verified through postman

	@Autowired
	private CourseServiceImpl courseService;
	
	@Autowired
	private EmployeeServiceImpl employeeService;
		
	private Employee employee;

	// working on this
	// -get a course and check if there is any available spot
	// 10 slots per sales person
	// 4 persons in total
	
	// in the future let this amount of slots and sales dynamically
	// regarding adm orders

	// where will I create the method to this verification before submitcourse ?

	@PostMapping("/submitCourse")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String booking(@RequestBody Course course) {	
		 courseService.save(course);
		 return "this course has been created successfully";
	}

	@GetMapping("/all")
	public List<Course> findAllCourses() {
		return courseService.findAll();
	}
	
	//courses created at the moment
	@GetMapping("/validCouses")
	public List<Course> findAllValidCourses() {
		return courseService.findAllValidCourses();
	}

	@GetMapping("/getById/{idCourse}")
	public Course findCourseById(@PathVariable Long idCourse) {
		return courseService.findOrThrowsException(idCourse);
	}

	@PutMapping("/{idCourse}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public String updateProduct(@RequestBody @Valid Course course, @PathVariable Long idCourse) {
		courseService.findOrThrowsException(idCourse);
		 courseService.update(course);
		 return "this course has been updated successfully";
	}

	@DeleteMapping("/{idCourse}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteProduct(@PathVariable Long idCourse) {
		courseService.deleteCourse(idCourse);
		return "this course has been deleted successfully";

	}

}
