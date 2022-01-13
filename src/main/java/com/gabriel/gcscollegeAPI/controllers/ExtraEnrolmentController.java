package com.gabriel.gcscollegeAPI.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.exception.BusinesException;
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.ExtraEnrolment;
import com.gabriel.gcscollegeAPI.services.EnrolmentServiceImpl;
import com.gabriel.gcscollegeAPI.services.ExtraEnrolmentService;
import com.gabriel.gcscollegeAPI.services.ExtraService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/{enrolmentID}/extra-enrolments")
public class ExtraEnrolmentController {

	@Autowired
	private ExtraEnrolmentService extraEnrolmentService;
	
	@Autowired
	private EnrolmentServiceImpl enrolmentService;
	
	
	@Autowired
	private ExtraService extraService;

	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Enrolment submission(@PathVariable Long enrolmentID, @RequestBody @Valid List<ExtraEnrolment> list) {
		Enrolment enrolment = enrolmentService.findOrThrowsException(enrolmentID);
		if (list.isEmpty()) {
			throw new BusinesException("The list of extras cannot be empty");
		}
		
		List<ExtraEnrolment> lisSaved = extraEnrolmentService.save(list, enrolment);
		
		Double totalExtras = lisSaved.stream().collect(Collectors.summingDouble(e -> e.getTotalParcial().doubleValue()));
		
		enrolment.sumWithExtras(new BigDecimal(totalExtras));
		
		enrolment.setExtras(lisSaved);
		return enrolmentService.update(enrolment);

	}

}