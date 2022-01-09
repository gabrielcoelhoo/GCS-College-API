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
import com.gabriel.gcscollegeAPI.repositories.CourseRepository;
import com.gabriel.gcscollegeAPI.services.CourseServiceImpl;
import com.gabriel.gcscollegeAPI.services.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/courses")
public class CourseControllers {

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private EmployeeServiceImpl employeeService;

	@Autowired
	private CourseRepository courseRepository;

	private Employee employee;

	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String booking(@RequestBody Course course) {
		courseService.save(course);
		return "this course has been created successfully";
	}

	@GetMapping("/all")
	public List<Course> findAllCourses() {
		return courseService.findAll();
	}

	// courses created at the moment
	@GetMapping("/validCouses")
	public List<Course> findAllValidCourses() {
		return courseService.findAllValidCourses();
	}

	@GetMapping("/getById/{idCourse}")
	public Course findCourseById(@PathVariable Long idCourse) {
		return courseService.findOrThrowsException(idCourse);
	}

	@PutMapping("/update/{idCourse}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public Course update(@RequestBody @Valid Course repCourse, @PathVariable Long idCourse) {

		Course courseBD = courseService.findOrThrowsException(idCourse);

		return courseRepository.findById(idCourse).map(course -> {
			course.setCourseEnd(repCourse.getCourseEnd());
			course.setCourseStart(repCourse.getCourseStart());
			course.setLevel(repCourse.getLevel());
			course.setPeriod(repCourse.getPeriod());
			course.setVacancies(repCourse.getVacancies());
			return courseRepository.save(course);
		}).orElseGet(() -> {
			repCourse.setId(idCourse);
			return courseRepository.save(repCourse);
		});

	}

	@DeleteMapping("/delete/{idCourse}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteProduct(@PathVariable Long idCourse) {
		courseService.deleteCourse(idCourse);
		return "this course has been deleted successfully";

	}

}
