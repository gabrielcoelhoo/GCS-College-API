package com.gabriel.gcscollegeAPI.services;


import java.math.BigDecimal;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.DoubleSummaryStatistics;
>>>>>>> 993546fc88a1b5fe520acdb90a2d57303a490c6d
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.BusinesException;
import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.Employee;
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.Extra;
import com.gabriel.gcscollegeAPI.model.ExtraEnrolment;
import com.gabriel.gcscollegeAPI.model.Status;
import com.gabriel.gcscollegeAPI.repositories.EnrolmentRepository;


@Service
public class EnrolmentServiceImpl {
	
	@Autowired
	private EnrolmentRepository enrolmentRepository;
	
	@Autowired
	private CourseServiceImpl courseService;
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	
	@Autowired
	private ExtraService extraService;
	
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
		
		
		Employee employee = employeeService.getEmployeeAvailable();
		employee.increaseQuantityEnrolments();
		employeeService.update(employee);
		
		
		enrolment.setEmployee(employee);
		
		enrolment.setTotal(enrolment.getCourse().getPrice());
		List<ExtraEnrolment> listReady = new ArrayList<>();
		
		if (!enrolment.getExtras().isEmpty()) {
			listReady = validExtrasEnrolment(enrolment);
		}
		
		enrolment.setExtras(listReady);
		
		
		if(!enrolment.getExtras().isEmpty()) {
			Double  totalExtras = enrolment.getExtras().stream().collect(Collectors.summingDouble(e -> e.getTotalParcial().doubleValue()));
			enrolment.sumWithExtras(new BigDecimal(totalExtras));
		}
		
		
		return enrolmentRepository.save(enrolment);
	}
	
	
	private List<ExtraEnrolment> validExtrasEnrolment(Enrolment e){
		 List<ExtraEnrolment> list = e.getExtras();
		 
		 list.forEach(item ->{
			 Extra extra = extraService.findByIDOrThrowsException(item.getId());
			 item.setExtra(extra);
			 BigDecimal total = extra.getPrice().multiply(new BigDecimal(item.getQuantity()));
			 item.setTotalParcial(total);

		 });
		 
		 return list;
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
	public Enrolment updateStatus(Long idEnrolment, String status) {
		
		Enrolment enrolment = findOrThrowsException(idEnrolment);
		Status current = enrolment.getStatus();
		Status statusInput = Status.findByName(status);
		
		if(statusInput == null) {
			throw new BusinesException(String.format("The status %s is invalid", status));
		}
		
		if(statusInput == Status.SENT) {
			throw new BusinesException(String.format("Status %d invalid", status));
		}
		
		if(current == Status.COMPLETED ||current == Status.REFUSED) {
			if(statusInput == current) {
				throw new BusinesException("You are informing the same status");
			}
			
			if(statusInput == Status.SENT) {
				throw new BusinesException(String.format("The current status %s cannot have the status %s", current, status));
			}
		}
		
		enrolment.changeStatus(statusInput);
		
		Employee employee = enrolment.getEmployee();
		employee.decreaseQuantityEnrolments();
		employeeService.save(employee);
		
		enrolment.setEmployee(employee);
		

        return enrolmentRepository.save(enrolment);
	}

	@Transactional
	public Enrolment updateEnrolment(Long idEnrolment, Long idnewCourse) {
		Enrolment enrolment = findOrThrowsException(idEnrolment);
		if(enrolment.getStatus() != Status.SENT) {
			throw new BusinesException(String.format("The enrolment of id %s cannot be updated", idEnrolment));
		}
		
		Course course = courseService.findOrThrowsException(idnewCourse);
		course.decreaseVacancies();
		courseService.update(course);
		
		enrolment.setCourse(course);
		
		return save(enrolment);
		
	}
	
	@Transactional
	public Enrolment update(Enrolment e) {
		return enrolmentRepository.save(e);
	}
	public Enrolment findOrThrowsException(Long id) {
		return enrolmentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The enrolment of id %d was not found!", id)));
	}

}