package com.gabriel.gcscollegeAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.services.StudentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class StudentControllers {
	
	@Autowired
	private StudentService studentService;

	@PostMapping("submitStudent")
	public String submission(@RequestBody Student student) {
		
		studentService.saveStudent(student);
		
		return "new student is added";
		
	}
	
	@PostMapping("userLogin")
	public String login(@RequestBody Login login) {
		
		
		return studentService.login(login);
		
		
	}
	
	@GetMapping("getAll")
	public List<Student> getAllStudents(){
		
		return studentService.getAllStudents();
		
	}
	

}

//String dateStr = obj.getString("birthdate");
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//Date birthDate = sdf.parse(dateStr);
////then
//user.setBirthdate(birthDate);
