package com.gabriel.gcscollegeAPI.controllers;

import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.model.User;
import com.gabriel.gcscollegeAPI.repositories.UserRepository;
import com.gabriel.gcscollegeAPI.services.UserServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/users")
public class UserControllers {
	
	private String SECRET_KEY = "secret";

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/{userID}")
	public User findByID(@PathVariable Long userID) {
		
		return userServiceImpl.findByIDOrThrowsException(userID);
		
//		if(studentService.findByID(studentID)) {
//			return new ResponseEntity<>(, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>("User with ID = {studentID} not found", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	
	@GetMapping("/all")
	public List<User> findAllCourses() {
		return userServiceImpl.findAll();
	}

	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String submission(@RequestBody User user) {
		
//		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		    String encodedPassword = passwordEncoder.encode(user.getPassword());
//		    user.setPassword(encodedPassword);

		userServiceImpl.saveUser(user);
		return "this student has been created successfully";

	}

	@PutMapping("/update/{studentID}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public User update(@RequestBody @Valid User repUser, @PathVariable Long userId) {
		User userBD = userServiceImpl.findByIDOrThrowsException(userId);

		return userRepository.findById(userId)
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
			    	  repUser.setId(userId);
			        return userRepository.save(repUser);
			      });	
		
		//method taken from 
		//https://spring.io/guides/tutorials/rest/

	}

//	@PostMapping("/userLogin")
//	public Token login(@RequestBody User user) {
//
//		return userServiceImpl.login(user);
//
//	}
	
	@DeleteMapping("/delete/{idCourse}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteProduct(@PathVariable Long idCourse) {
	userServiceImpl.deleteUser(idCourse);
		return "this course has been deleted successfully";

	}


	// creation of token

	private Token createJWT(String id, String subject, String issuer) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);
		// https://github.com/oktadev/okta-java-jwt-example/blob/master/src/main/java/com/okta/createverifytokens/JWTDemo.java
		// Here shows how to add expiration.
		return new Token(builder.compact());
	}

	private Claims verifyToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
		return claims;
	}


}