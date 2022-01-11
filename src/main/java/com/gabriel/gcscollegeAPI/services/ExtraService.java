package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Extra;
import com.gabriel.gcscollegeAPI.model.User;
import com.gabriel.gcscollegeAPI.repositories.ExtraRepository;

@Service
public class ExtraService {

	@Autowired
	private ExtraRepository extraRepository;


	public List<Extra> findAll() {
		return extraRepository.findAll();
	}


	public Extra findOrThrowsException(Long id) {
		return extraRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The extra service of id %d was not found!", id)));
	}


	public void deleteExtra(Long idExtras) {
		findOrThrowsException(idExtras);
		extraRepository.deleteById(idExtras);
		
	}


	public Extra findByIDOrThrowsException(Long extraId) {
		return extraRepository.findById(extraId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The extra of id %d was not found!", extraId)));
	}
	


	public Object findById(Long extraId) {
		return extraRepository.findById(extraId);
	}


	public Extra saveExtra(Extra extra) {
		return extraRepository.save(extra);
	}

}
