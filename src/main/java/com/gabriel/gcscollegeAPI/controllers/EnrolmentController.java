package com.gabriel.gcscollegeAPI.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.Employee;
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.repositories.CourseRepository;
import com.gabriel.gcscollegeAPI.services.CourseServiceImpl;
import com.gabriel.gcscollegeAPI.services.EmployeeServiceImpl;
import com.gabriel.gcscollegeAPI.services.EnrolmentServiceImpl;
import com.gabriel.gcscollegeAPI.services.StudentServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/enrolments")
public class EnrolmentController {

	@Autowired
	private EnrolmentServiceImpl enrolmentService;
	
	@Autowired
	private CourseServiceImpl courseService;
	
	@Autowired
	private StudentServiceImpl studentService;
	
	private Employee employee;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String booking(@RequestBody Enrolment enrolment) {

		//Student found = studentService.findByIDOrThrowsException(enrolment.getStudent().getId());
		Course courseFound = courseService.findOrThrowsException(enrolment.getCourse().getId());

		enrolment.setCourse(courseFound);
		//enrolment.setStudent(found);
		enrolment = enrolmentService.save(enrolment);
		 
		return "this enrolment has been created successfully";

	}

	@GetMapping("/all")
	public List<Enrolment> findAllEnrolment() {
		return enrolmentService.findAll();
	}

	@GetMapping("/{idEnrolment}")
	public Enrolment findEnrolmentById(@PathVariable Long idEnrolment) {
		return enrolmentService.findOrThrowsException(idEnrolment);
	}

	@PutMapping("/{idCourse}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String updateidEnrolment(@RequestBody Map<String, String> map, @PathVariable Long idEnrolment) {
		Enrolment enrolment = enrolmentService.findOrThrowsException(idEnrolment);
		String status = map.get("status");
		status = status.toUpperCase();
		enrolmentService.updateStatus(enrolment, status);
		
		 return "this enrolment has been updated successfully";

	}

	@DeleteMapping("/{idEnrolment}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteEnrolment(@PathVariable Long idEnrolment) {
		enrolmentService.deleteCourse(idEnrolment);
		
		 return "this enrolment has been deleted successfully";


	}

}
