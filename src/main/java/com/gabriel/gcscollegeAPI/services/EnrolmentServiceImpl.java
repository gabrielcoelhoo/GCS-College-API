package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.repositories.EnrolmentRepository;


@Service
public class EnrolmentServiceImpl {
	
	@Autowired
	private EnrolmentRepository enrolmentRepository;
	
	public List<Enrolment> findAll() {
		return enrolmentRepository.findAll();
	}
	
	@Transactional
	public Enrolment save(Enrolment enrolment) {
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
	public Enrolment update(Enrolment enrolment) {
		Enrolment enrolmentFound = findOrThrowsException(enrolment.getId());

        return enrolmentRepository.save(enrolmentFound);
	}

	public Enrolment findOrThrowsException(Long id) {
		return enrolmentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The enrolment of id %d was not found!", id)));
	}

}
