package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Token;

public interface StudentService {
	
	public Student saveStudent(Student student);
	
	public List<Student> getAllStudents();
	
	public Token login(Login login);

}
