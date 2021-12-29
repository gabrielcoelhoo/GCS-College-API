package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.repositories.CourseRepository;


@Service
public class CourseServiceImpl {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	@Transactional
	public Course save(Course course) {
		return courseRepository.save(course);
	}
	
	
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}
	
	@Transactional
    public void deleteCourse(Long id) {
		findOrThrowsException(id);
    	courseRepository.deleteById(id);
    }
    
	@Transactional
	public Course update(Course course) {
		Course existingCourse = findOrThrowsException(course.getId());

        return courseRepository.save(existingCourse);
	}

	public Course findOrThrowsException(Long id) {
		return courseRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The course of id %d was not found!", id)));
	}

}
