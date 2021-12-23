package com.gabriel.gcscollegeAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;


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
		
		return studentRepository.findAll();
	}

}
