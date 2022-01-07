package com.gabriel.gcscollegeAPI.services;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.InvalidEmailException;
import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Course;
import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Role;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.repositories.RoleRepository;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class StudentServiceImpl {

	@Autowired
	private StudentRepository studentRepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private RoleRepository roleRepository;

//	private String SECRET_KEY = "secret";	
	

//	public boolean findByID(Long id) {
//		return studentRepository.findById(id) != null;
////				() -> new ResourceNotFoundException(String.format("The Student of id %d was not found", id)));
//	}
	 
	 public Optional<Student> findUserByEmail(String email){
		return studentRepository.findUserByEmail(email);
		 
	 }

	@Transactional
	public Student saveStudent(Student student) {

		Optional<Optional<Student>> found = Optional.ofNullable(studentRepository.findUserByEmail(student.getEmail()));
		
		if (found.isPresent() && !found.get().equals(student)) {
			throw new InvalidEmailException(String.format("The email %s is already registered", student.getEmail()));
		}
		return studentRepository.save(student);
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
		studentRepository.deleteById(id);
	}


	@Transactional
	public Student updateStudent(Student repStudent, Long studentID) {
		
		return studentRepository.findById(studentID)
			      .map(student -> {
			    	  student.setAddress(repStudent.getAddress());
			    	  student.setCountry(repStudent.getCountry());
			    	  student.setEmail(repStudent.getEmail());
			    	  student.setName(repStudent.getName());
			    	  student.setPassword(repStudent.getPassword());
			    	  student.setPhoneNumber(repStudent.getPhoneNumber());
			    	  student.setStudentComments(repStudent.getStudentComments());
			    	  student.setSurname(repStudent.getSurname());
			        return studentRepository.save(student);
			      })
			      .orElseGet(() -> {
			    	  repStudent.setId(studentID);
			        return studentRepository.save(repStudent);
			      });	
		
		//method taken from 
		//https://spring.io/guides/tutorials/rest/
	}
	
	

//	public Token login(Login login) {
//
//		Student student = studentRepository.findByEmail(login.getEmail());
//		if (student == null) {
//			throw new RuntimeException("User does not exist.");
//		}
//		if (!student.getPassword().equals(login.getPassword())) {
//			throw new RuntimeException("Password mismatch.");
//		}
//		return createJWT("cbwa", student.getEmail(), "gabriel");
//	}
//
//
//	// creation of token
//
//	private Token createJWT(String id, String subject, String issuer) {
//		long nowMillis = System.currentTimeMillis();
//		Date now = new Date(nowMillis);
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//		byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
//		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//		// Let's set the JWT Claims
//		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
//				.signWith(signatureAlgorithm, signingKey);
//		// https://github.com/oktadev/okta-java-jwt-example/blob/master/src/main/java/com/okta/createverifytokens/JWTDemo.java
//		// Here shows how to add expiration.
//		return new Token(builder.compact());
//	}
//
//	private Claims verifyToken(String token) {
//		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
//		return claims;
//	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Student findByIDOrThrowsException(Long id) {
		return studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The Student of id %d was not found", id)));
	}

	
	///////////////////////////////////////////////////////////////

	    public void initRoleAndUser() {

	        Role adminRole = new Role();
	        adminRole.setRoleName("Admin");
	        adminRole.setRoleDescription("Admin role");
	        roleRepository.save(adminRole);

	        Role userRole = new Role();
	        userRole.setRoleName("User");
	        userRole.setRoleDescription("Default role for newly created record");
	        roleRepository.save(userRole);

	        Student adminUser = new Student();
	        adminUser.setAddress("4 henry st");
	        adminUser.setCountry("ireland");
	        adminUser.setEmail("admin@admin.com");
	        adminUser.setName("admin");
	        adminUser.setPassword(getEncodedPassword("admin"));
	        adminUser.setPhoneNumber("1234567890");
	        adminUser.setStudentComments("admin register");
	        adminUser.setSurname("boss");
	        adminUser.setUsername("admin@admin.com");
	        Set<Role> adminRoles = new HashSet<>();
	        adminRoles.add(adminRole);
	        adminUser.setRole(adminRoles);
	        studentRepository.save(adminUser);

	    }

	    public Student registerNewStudent(Student student) {
	        Role role = roleRepository.findById("Student").get();
	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(role);
	        student.setRole(userRoles);
	        student.setPassword(getEncodedPassword(student.getPassword()));

	        return studentRepository.save(student);
	    }

	    public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }
	}
	
