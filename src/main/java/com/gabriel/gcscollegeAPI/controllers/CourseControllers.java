package com.gabriel.gcscollegeAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.Employee;
import com.gabriel.gcscollegeAPI.repositories.CourseRepository;
import com.gabriel.gcscollegeAPI.services.CourseServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/courses")
public class CourseControllers {

	@Autowired
	private CourseServiceImpl courseService;
		
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

	@PostMapping("submitCourse")
	public void booking(@RequestBody Course course) {
		
		///  CREATE EMPLOY CLASSES
		
//		sizeOfList = courseService.getCourses().size();
//		
//		int sizeOfListNext = courseService.getCourses().size() + 1;
//		
//		System.out.println("course id = " + courseService.getCourseById(sizeOfList).getId());
//		
		//System.out.println("employee id = " + employee);
		
		System.out.println(employee);

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
	

	@GetMapping("/all")
	public List<Course> findAllCourses() {

		return courseService.getCourses();
	}

	@GetMapping("/courseById/{id}")
	public Course findCourseById(@PathVariable int id) {
		return courseService.getCourseById(id);
	}

	@PutMapping("/update")
	public Course updateProduct(@RequestBody Course course) {
		return courseService.updateCourse(course);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return courseService.deleteCourse(id);
	}

}
