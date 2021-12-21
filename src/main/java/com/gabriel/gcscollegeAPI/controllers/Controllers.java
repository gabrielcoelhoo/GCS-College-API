package com.gabriel.gcscollegeAPI.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class Controllers {

	@PostMapping("newUser")
	public void login(@RequestBody Map<String, Object> newUser) {

		System.out.println(newUser);
	}
	
	@PostMapping("bookingDetails")
	public void booking(@RequestBody Map<String, Object> bookingDetails ) {
		
	System.out.println(bookingDetails);
	}
	
	@PostMapping("submitStudent")
	public void registration(@RequestBody Map<String, Object> submitStudent ) {

	System.out.println(submitStudent);	
	}

}

//String dateStr = obj.getString("birthdate");
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//Date birthDate = sdf.parse(dateStr);
////then
//user.setBirthdate(birthDate);
