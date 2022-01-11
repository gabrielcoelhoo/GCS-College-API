package com.gabriel.gcscollegeAPI.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.exception.BusinesException;
import com.gabriel.gcscollegeAPI.model.Enrolment;
import com.gabriel.gcscollegeAPI.model.Extra;
import com.gabriel.gcscollegeAPI.model.ExtraEnrolment;
import com.gabriel.gcscollegeAPI.repositories.EnrolmentRepository;
import com.gabriel.gcscollegeAPI.repositories.ExtraEnrolmentRepository;

@Service
public class ExtraEnrolmentService {

	@Autowired
	private ExtraEnrolmentRepository extraEnrolmentRepository;
	
	@Autowired
	private EnrolmentServiceImpl enrolmentService;
	
	@Autowired
	private ExtraService extraService;

	@Autowired
	private EnrolmentRepository enrolmentRepository;
	public Enrolment save(List<ExtraEnrolment> list) {
		if (list.isEmpty()) {
			throw new BusinesException("The list of extras cannot be empty");
		}

		Enrolment enrolment = enrolmentService.findOrThrowsException(list.get(0).getEnrolment().getId());

		list.forEach(i -> {
			Extra extra = extraService.findByIDOrThrowsException(i.getExtra().getId());
			i.setExtra(extra);
			i.setEnrolment(enrolment);
			BigDecimal totalParcial = extra.getPrice().multiply(new BigDecimal(i.getQuantity()));
			enrolment.sumWithExtras(totalParcial);
			extraEnrolmentRepository.save(i);
		});
		//enrolment.setExtras(list);
		return enrolmentRepository.save(enrolment);

	}

}
