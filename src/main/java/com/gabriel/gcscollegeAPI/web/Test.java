package com.gabriel.gcscollegeAPI.web;

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
	
	@PostMapping("bookingDetails")
	public void login(@RequestBody Map<String, Object> bookingDetails ) {
		
	System.out.println(bookingDetails);
	}
	
	@GetMapping("submitStudent")
	public void login(String name, String address, String email, String phone,
			String country, String comments) {

	System.out.println(name);
	System.out.println(address);
	System.out.println(email);
	System.out.println(phone);
	System.out.println(country);
	System.out.println(comments);
	
	}

}
