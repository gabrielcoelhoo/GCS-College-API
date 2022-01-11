package com.gabriel.gcscollegeAPI.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Extra;
import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.model.User;
import com.gabriel.gcscollegeAPI.repositories.ExtraRepository;
import com.gabriel.gcscollegeAPI.repositories.UserRepository;
import com.gabriel.gcscollegeAPI.services.ExtraService;
import com.gabriel.gcscollegeAPI.services.UserServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/extras")
public class ExtraController {

	@Autowired
	private ExtraService extraService;
	
	@Autowired
	private ExtraRepository extraRepository;
	
	
	@GetMapping("/{extraId}")
	public Extra findByID(@PathVariable Long extraId) {
		return extraService.findByIDOrThrowsException(extraId);

	}
	
	@GetMapping("/all")
	public List<Extra> findAllCourses() {
		return extraService.findAll();
	}

	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Extra submission(@RequestBody Extra extra) {
		
//		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		    String encodedPassword = passwordEncoder.encode(user.getPassword());
//		    user.setPassword(encodedPassword);

		return extraService.saveExtra(extra);

	}

	@PutMapping("/update/{extraId}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public Extra update(@RequestBody @Valid Extra repExtra, @PathVariable Long extraId) {
		
		Extra extraBD = extraService.findByIDOrThrowsException(extraId);

		return extraRepository.findById(extraId)
			      .map(extra -> {
			    	  extra.setName(repExtra.getName());
			    	  extra.setPrice(repExtra.getPrice());
			    	  return extraRepository.save(extra);
			      })
			      .orElseGet(() -> {
			    	  repExtra.setId(extraId);
			        return extraRepository.save(repExtra);
			      });	
		
		//method taken from 
		//https://spring.io/guides/tutorials/rest/

	}
	
	@DeleteMapping("/delete/{extraId}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteProduct(@PathVariable Long extraId) {
		extraService.deleteExtra(extraId);
	return "this courses has been delted successfuly";
		

	}


}