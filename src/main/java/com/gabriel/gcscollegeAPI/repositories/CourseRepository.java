package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
		
	
}
