package com.gabriel.gcscollegeAPI.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.InvalidEmailException;
import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Role;
import com.gabriel.gcscollegeAPI.model.User;
import com.gabriel.gcscollegeAPI.repositories.RoleRepository;
import com.gabriel.gcscollegeAPI.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

//	public boolean findByID(Long id) {
//		return studentRepository.findById(id) != null;
////				() -> new ResourceNotFoundException(String.format("The Student of id %d was not found", id)));
//	}

	@Transactional
	public User saveStudent(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Optional<User> found = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
		
		if (found.isPresent() && !found.get().equals(user)) {
			throw new InvalidEmailException(String.format("The email %s is already registered", user.getEmail()));
		}
		return userRepository.save(user);
	}
	
//	public void verifyEmail(String email) {
//		Optional<Student> found = Optional.of(studentRepository.findByEmail(email));
//		if (found.isPresent()) {
//			throw new InvalidEmailException(String.format("The email %s is already registered", email));
//		}
//	}
	
	@Transactional
	public void deleteStudent(Long id) {
		findByIDOrThrowsException(id);
		userRepository.deleteById(id);
	}


	@Transactional
	public User updateStudent(User repStudent, Long studentID) {
		
		return userRepository.findById(studentID)
			      .map(student -> {
			    	  student.setAddress(repStudent.getAddress());
			    	  student.setCountry(repStudent.getCountry());
			    	  student.setEmail(repStudent.getEmail());
			    	  student.setName(repStudent.getName());
			    	  student.setPassword(repStudent.getPassword());
			    	  student.setPhoneNumber(repStudent.getPhoneNumber());
			    	  student.setStudentComments(repStudent.getStudentComments());
			    	  student.setSurname(repStudent.getSurname());
			        return userRepository.save(student);
			      })
			      .orElseGet(() -> {
			    	  repStudent.setId(studentID);
			        return userRepository.save(repStudent);
			      });	
		
		//method taken from 
		//https://spring.io/guides/tutorials/rest/
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByIDOrThrowsException(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The Student of id %d was not found", id)));
	}


	public User saveOrUpdate(User user) {
		return userRepository.saveAndFlush(user);
	}

}