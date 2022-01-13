package com.gabriel.gcscollegeAPI.controllers;

import java.util.List;

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
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.EnrolmentInput;
import com.gabriel.gcscollegeAPI.model.User;
import com.gabriel.gcscollegeAPI.services.CourseServiceImpl;
import com.gabriel.gcscollegeAPI.services.EnrolmentServiceImpl;
import com.gabriel.gcscollegeAPI.services.ExtraService;
import com.gabriel.gcscollegeAPI.services.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/enrolments")
public class EnrolmentController {

	@Autowired
	private EnrolmentServiceImpl enrolmentService;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private UserServiceImpl studentService;


	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Enrolment booking(@RequestBody EnrolmentInput input) {

		User found = studentService.loginEmailCheck(input.getEmail());
		Course courseFound = courseService.findOrThrowsException(input.getCourseID());

		Enrolment enrolment = new Enrolment();
		enrolment.setCourse(courseFound);
		enrolment.setUser(found);
		enrolment.setExtras(input.getExtras());

		enrolment = enrolmentService.save(enrolment);

		return enrolment;

	}

	@GetMapping("/all")
	public List<Enrolment> findAllEnrolment() {
		return enrolmentService.findAll();
	}

	@GetMapping("/{idEnrolment}")
	public Enrolment findEnrolmentById(@PathVariable Long idEnrolment) {
		return enrolmentService.findOrThrowsException(idEnrolment);
	}

	@PutMapping("/{idEnrolment}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updateEnrolment(@RequestBody Long idCourse, @PathVariable Long idEnrolment) {
		enrolmentService.updateEnrolment(idEnrolment, idCourse);
	}

	@PutMapping("/{idEnrolment}/{status}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void changeStatusEnrolment(@PathVariable Long idEnrolment, @PathVariable String status) {
		enrolmentService.updateStatus(idEnrolment, status);
	}

	@DeleteMapping("/{idEnrolment}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteEnrolment(@PathVariable Long idEnrolment) {
		enrolmentService.deleteCourse(idEnrolment);

		return "this enrolment has been deleted successfully";

	}

}