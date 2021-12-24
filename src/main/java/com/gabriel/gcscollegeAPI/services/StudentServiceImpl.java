package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;
import com.gabriel.gcscollegeAPI.model.Token;


@Service
public class StudentServiceImpl implements StudentService{
	
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	
	}


	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String login(Login login) {
		
		Student student = studentRepository.findByUsername(login.getUsername());
		if(student == null) {
			throw new RuntimeException("User does not exist.");
		}
		if(!student.getPassword().equals(login.getPassword())) {
			throw new RuntimeException("Password mismatch.");
		}
		return "login successful";
	}
		
//		studentRepository.finfindbyemailandid(
//				return createJWT("cbwa", user.getUsername(), "david");
//		}else
//			throw new RuntimeException("User not found");
			
	
	
	

}
