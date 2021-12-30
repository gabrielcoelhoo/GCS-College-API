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

	private int sizeOfList;

	

	
	// I KNOW THAT THERE IS A LOT OF COMMENTS
	// BUT AS SOON AS I HAVE AN IDEA I NEED TO DECLARE IT
	//I WILL REMOVE MOST OF THEM WHEN I FINISH
	
	

	// working on this
	// -get a course and check if there is any available spot
	// 10 slots per sales person
	// 4 persons in total
	
	//flow
	//check spots availability? 
	
	//if yes send to a specific employee
	//save in employee course db 
	//save in course db
	
	//else
	//return a message "there is not availability at the moment"
	// in the future save it and set in a queue
	

	// in the future let this amount of slots and sales dynamically
	// regarding adm orders

	// where will I create the method to this verification before submitcourse ?

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Enrolment booking(@RequestBody Enrolment enrolment) {

		/// CREATE EMPLOY CLASSES

//		sizeOfList = courseService.getCourses().size();
//		
//		int sizeOfListNext = courseService.getCourses().size() + 1;
//		
//		System.out.println("course id = " + courseService.getCourseById(sizeOfList).getId());
//		
		// System.out.println("employee id = " + employee);

		Student found = studentService.findByIDOrThrowsException(enrolment.getStudent().getId());
		Course courseFound = courseService.findOrThrowsException(enrolment.getCourse().getId());

		enrolment.setCourse(courseFound);
		enrolment.setStudent(found);
		enrolment = enrolmentService.save(enrolment);
		return enrolment;

	}
		
		//System.out.println("employee id " + employee.getId());
		
				//return courseService.saveCourse(course);

//		if (quantity <= 10) {
//
//			if (employee.getId() == 1) {
//
//				employeeCourseServiceImpl.saveEmployeeCourse(employeeCourse);
//
//			}
//		} else if (quantity >= 11 & quantity <= 20) {
//			
//			if (employee.getId() == 2) {
//
//				employeeCourseServiceImpl.saveEmployeeCourse(employeeCourse);
//
//			}
//
//		} else if (quantity >= 21 & quantity <= 30) {
//			
//			if (employee.getId() == 3) {
//
//				employeeCourseServiceImpl.saveEmployeeCourse(employeeCourse);
//
//			}
//
//		} else if (quantity >= 31 & quantity <= 40) {
//			
//			if (employee.getId() == 4) {
//
//				employeeCourseServiceImpl.saveEmployeeCourse(employeeCourse);
//
//			}
//
//		} else {
//			// selles are fully booked
//		}
//
//		System.out.println(quantity);


	
	
//	public boolean hasAvailableSpots(Course course, Employee employee) {
//		
//		
//		courseService.getCourseById(course.getId());
//		employee
//
//		
//		return true;
//	}
//	
	

	@GetMapping
	public List<Enrolment> findAllCourses() {
		return enrolmentService.findAll();
	}

	@GetMapping("/{idCourse}")
	public Enrolment findCourseById(@PathVariable Long idCourse) {
		return enrolmentService.findOrThrowsException(idCourse);
	}

	@PutMapping("/{idCourse}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Enrolment updateProduct(@RequestBody Map<String, String> map, @PathVariable Long idCourse) {
		Enrolment enrolment = enrolmentService.findOrThrowsException(idCourse);
		String status = map.get("status");
		status = status.toUpperCase();
		return enrolmentService.updateStatus(enrolment, status);
	}

	@DeleteMapping("/{idCourse}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Long idCourse) {
		enrolmentService.deleteCourse(idCourse);

	}

}
