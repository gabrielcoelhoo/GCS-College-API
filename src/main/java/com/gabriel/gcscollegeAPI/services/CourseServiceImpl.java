package com.gabriel.gcscollegeAPI.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<Course> findAllValidCourses() {
		List<Course> courses = courseRepository.findAll();

		List<Course> validCourses = courses.stream().filter(e -> courseValid(e.getCourseStart(), e.getCourseEnd()))
				.filter(c -> c.getVacancies() > 0).collect(Collectors.toList());

		return validCourses;
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	private boolean courseValid(LocalDate start, LocalDate end) {
		LocalDate current = LocalDate.now();
		return current.isBefore(start) || current.isAfter(end);
	}

	@Transactional
	public Course save(Course course) {
		return courseRepository.save(course);
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
