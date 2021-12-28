package com.gabriel.gcscollegeAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.services.CourseServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/courses")
public class CourseControllers {
	
	@Autowired
	private CourseServiceImpl courseService;
	
	@PostMapping("submitCourse")
	public Course booking(@RequestBody Course course) {
		
		return courseService.saveCourse(course);
			
	}

}
