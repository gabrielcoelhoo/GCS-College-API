package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Extra;
import com.gabriel.gcscollegeAPI.repositories.ExtraReporsitory;

@Service
public class ExtraService {

	@Autowired
	private ExtraReporsitory extraRepository;


	public List<Extra> findAll() {
		return extraRepository.findAll();
	}


	public Extra findOrThrowsException(Long id) {
		return extraRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The extra service of id %d was not found!", id)));
	}

}
