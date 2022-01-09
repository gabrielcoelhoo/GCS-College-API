package com.gabriel.gcscollegeAPI.services;
import java.awt.RenderingHints.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.InvalidEmailException;
import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.model.User;
import com.gabriel.gcscollegeAPI.repositories.RoleRepository;
import com.gabriel.gcscollegeAPI.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private String SECRET_KEY = "secret";	
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	

//	public boolean findByID(Long id) {
//		return studentRepository.findById(id) != null;
////				() -> new ResourceNotFoundException(String.format("The Student of id %d was not found", id)));
//	}

	@Transactional
	public User saveUser(User user) {
		
		user.setPassword(user.getPassword());

		Optional<User> found = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
		
		if (found.isPresent() && !found.get().equals(user)) {
			throw new InvalidEmailException(String.format("The email %s is already registered", user.getEmail()));
		}
		return userRepository.save(user);
	}
	
//	public void verifyEmail(String email) {
//		Optional<User> found = Optional.of(studentRepository.findByEmail(email));
//		if (found.isPresent()) {
//			throw new InvalidEmailException(String.format("The email %s is already registered", email));
//		}
//	}
	
	@Transactional
	public void deleteUser(Long id) {
		findByIDOrThrowsException(id);
		userRepository.deleteById(id);
	}


	@Transactional
	public User updateUser(User repUser, Long userID) {
		
		return userRepository.findById(userID)
			      .map(user -> {
			    	  user.setAddress(repUser.getAddress());
			    	  user.setCountry(repUser.getCountry());
			    	  user.setEmail(repUser.getEmail());
			    	  user.setName(repUser.getName());
			    	  user.setPassword(repUser.getPassword());
			    	  user.setPhoneNumber(repUser.getPhoneNumber());
			    	  user.setUserComments(repUser.getUserComments());
			    	  user.setSurname(repUser.getSurname());
			        return userRepository.save(user);
			      })
			      .orElseGet(() -> {
			    	  repUser.setId(userID);
			        return userRepository.save(repUser);
			      });	
		
		//method taken from 
		//https://spring.io/guides/tutorials/rest/
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByIDOrThrowsException(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The User of id %d was not found", id)));
	}


	public User saveOrUpdate(User user) {
		return userRepository.saveAndFlush(user);
	}
	
	

}