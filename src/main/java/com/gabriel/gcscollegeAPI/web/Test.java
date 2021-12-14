package com.gabriel.gcscollegeAPI.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class Test {
	
	@RequestMapping("/")
	public String getGreeting() {
		return "helooooo";
	}
	
	@GetMapping("userDetails")
	public void login(String email, String password) {

	System.out.println(email);
	System.out.println(password);
	}
	
	@GetMapping("bookingDetails")
	public void login(String englishCourse, String accomodation, String transfer, String comments) {

	System.out.println(englishCourse);
	System.out.println(accomodation);
	System.out.println(transfer);
	System.out.println(comments);
	}

}
