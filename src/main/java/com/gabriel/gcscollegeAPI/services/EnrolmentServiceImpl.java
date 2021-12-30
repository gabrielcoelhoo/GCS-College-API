package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.BusinesException;
import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.Status;
import com.gabriel.gcscollegeAPI.repositories.EnrolmentRepository;


@Service
public class EnrolmentServiceImpl {
	
	@Autowired
	private EnrolmentRepository enrolmentRepository;
	
	@Autowired
	private CourseServiceImpl courseService;
	
	public List<Enrolment> findAll() {
		return enrolmentRepository.findAll();
	}
	
	@Transactional
	public Enrolment save(Enrolment enrolment) {
		Course course = enrolment.getCourse();
		
		if(course.getVacancies() == 0) {
			throw new BusinesException(String.format("The course of id %d does not have vacancies", course.getId()));
		}
		course.decreaseVacancies();
		course= courseService.save(course);
		return enrolmentRepository.save(enrolment);
	}
	
	
	public List<Enrolment> getCourses() {
		return enrolmentRepository.findAll();
	}
	
	@Transactional
    public void deleteCourse(Long id) {
		findOrThrowsException(id);
    	enrolmentRepository.deleteById(id);
    }
    
	@Transactional
	public Enrolment updateStatus(Enrolment enrolment, String status) {
		Status current = enrolment.getStatus();
		Status statusInput = Status.findByName(status);
		
		if(statusInput == null) {
			throw new BusinesException(String.format("The status %s is invalid", status));
		}
		
		if(statusInput == Status.SENT) {
			throw new BusinesException(String.format("Status %d invalid", status));
		}
		
		if(current == Status.ANALYSIS) {
			if(statusInput == current) {
				throw new BusinesException("You are informing the same status");
			}
		}
		
		if(current == Status.COMPLETED) {
			if(statusInput == current) {
				throw new BusinesException("You are informing the same status");
			}
			
			if(statusInput == Status.ANALYSIS) {
				throw new BusinesException(String.format("The current status %s cannot have the status %s", current, status));
			}
		}
		
		enrolment.changeStatus(statusInput);

        return enrolmentRepository.save(enrolment);
	}

	public Enrolment findOrThrowsException(Long id) {
		return enrolmentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The enrolment of id %d was not found!", id)));
	}

}
