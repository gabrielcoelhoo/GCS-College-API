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

		return courseRepository.save(course);
			
	}

	public List<Course> getCourses() {

		return courseRepository.findAll();
	}
	
	
    public String deleteCourse(long id) {
    	courseRepository.deleteById(id);
        return "product removed !! " + id;
    }
    

	public Course getCourseById(int id) {
		return courseRepository.findById((long) id).orElse(null);
	}


	public Course updateCourse(Course course) {
		
		Course existingCourse = courseRepository.findById(course.getId()).orElse(null);

        return courseRepository.save(existingCourse);
	}



}
