package com.gabriel.gcscollegeAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.EmployeeCourse;
import com.gabriel.gcscollegeAPI.repositories.CourseRepository;
import com.gabriel.gcscollegeAPI.repositories.EmployeeCourseRepository;

public class EmployeeCourseImpl {
	
	@Autowired
	private EmployeeCourseRepository employeeCourseRepository;
	
	@Transactional
	public EmployeeCourse saveEmployeeCourseRepository(EmployeeCourse employeeCourse) {

		return employeeCourseRepository.save(employeeCourse)  ;
			
	}

}
