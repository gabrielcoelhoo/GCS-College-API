package com.gabriel.gcscollegeAPI.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public List<ExtraEnrolment> save(List<ExtraEnrolment> list, Enrolment enrolment) {

		List<ExtraEnrolment> listReady = list.stream().map(i -> {
			Extra extra = extraService.findByIDOrThrowsException(i.getExtra().getId());
			i.setExtra(extra);
			i.setEnrolment(enrolment);
			BigDecimal total = extra.getPrice().multiply(new BigDecimal(i.getQuantity()));
			i.setTotalParcial(total);

			return i;
		}).collect(Collectors.toList());
		return extraEnrolmentRepository.saveAll(listReady);
	}

}
