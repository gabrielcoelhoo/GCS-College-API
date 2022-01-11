package com.gabriel.gcscollegeAPI.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.ExtraEnrolment;
import com.gabriel.gcscollegeAPI.services.ExtraEnrolmentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/extra-enrolments")
public class ExtraEnrolmentController {

	@Autowired
	private ExtraEnrolmentService extraEnrolmentService;

	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Enrolment submission(@RequestBody @Valid List<ExtraEnrolment> list) {
		
		return extraEnrolmentService.save(list);

	}

}