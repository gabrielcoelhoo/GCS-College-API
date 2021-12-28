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
import com.gabriel.gcscollegeAPI.services.CourseServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/courses")
public class CourseControllers {
	
	@Autowired
	private CourseServiceImpl courseService;
	
	@PostMapping("submitCourse")
	public Course booking(@RequestBody Course course) {
		
		int quantity = courseService.getCourses().size() + 1;
		
		if(quantity <= 10) {
			
		}else if(quantity >= 11 & quantity <= 20) {
			
		}else if(quantity >= 21 & quantity <= 30) {
			
		}else if(quantity >= 31 & quantity <= 40) {
			
		}else {
		//selles are fully booked
		}
		
		System.out.println(quantity);
		
		return courseService.saveCourse(course);
			
	}
	
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
