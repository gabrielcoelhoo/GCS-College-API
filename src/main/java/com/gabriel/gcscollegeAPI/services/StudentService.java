package com.gabriel.gcscollegeAPI.services;

import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Token;

public interface StudentService {
	
	public Student saveStudent(Student student);
	
	public Token login(Login login);
	
	
}
