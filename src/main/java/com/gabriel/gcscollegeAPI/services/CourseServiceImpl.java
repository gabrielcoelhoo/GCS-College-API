package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.repositories.CourseRepository;


@Service
public class CourseServiceImpl {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Transactional
	public Course saveCourse(Course course) {
		
//		int coursesQuantity = 0;
//		
//		coursesQuantity = studentRepository.findAllCourses().size();
//		
//		
//		System.out.println(coursesQuantity);
		

		return courseRepository.save(course);
		
		
	}

	public List<Course> getCourses() {

		return courseRepository.findAll();
	}

}
