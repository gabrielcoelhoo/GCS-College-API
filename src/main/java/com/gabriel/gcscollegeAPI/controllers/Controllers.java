package com.gabriel.gcscollegeAPI.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class Controllers {
	
	@Autowired
	private StudentRepository studentRepository;

	@PostMapping("submitStudent")
	public void login(@RequestBody Map<String, String> submitStudent ) {
		
		Student student = new Student();
		
		for (Map.Entry<String, String> entry : submitStudent.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("name") ) {
				student.setName(entry.getValue());
				
			}else if(entry.getKey().equalsIgnoreCase("surname") ) {
				student.setSurname(entry.getValue());
				
			}else if(entry.getKey().equalsIgnoreCase("country") ) {
				student.setCountry(entry.getValue());
				
			}else if(entry.getKey().equalsIgnoreCase("email") ) {
				student.setEmail(entry.getValue());
				
			}else if(entry.getKey().equalsIgnoreCase("password") ) {
				student.setPassword(entry.getValue());
				
			}else if(entry.getKey().equalsIgnoreCase("phone") ) {
				student.setPhoneNumber(entry.getValue());
				
			}else if(entry.getKey().equalsIgnoreCase("comments") ) {
				student.setStudentComments(entry.getValue());
				
			}else if(entry.getKey().equalsIgnoreCase("address") ) {
				student.setAddress(entry.getValue());
			}
		}
		
		studentRepository.save(student);
		
	}
	
	@PostMapping("bookingDetails")
	public void booking(@RequestBody Map<String, Object> bookingDetails ) {
		
	System.out.println(bookingDetails);
	}
	

}

//String dateStr = obj.getString("birthdate");
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//Date birthDate = sdf.parse(dateStr);
////then
//user.setBirthdate(birthDate);
