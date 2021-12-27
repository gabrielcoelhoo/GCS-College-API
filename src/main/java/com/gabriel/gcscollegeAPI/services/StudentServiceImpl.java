package com.gabriel.gcscollegeAPI.services;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.gcscollegeAPI.exception.InvalidEmailException;
import com.gabriel.gcscollegeAPI.exception.ResourceNotFoundException;
import com.gabriel.gcscollegeAPI.model.Login;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.model.Token;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class StudentServiceImpl {

	@Autowired
	private StudentRepository studentRepository;

	private String SECRET_KEY = "secret";

	public Student findByIDOrThrowsException(Long id) {
		return studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The Student of id %d was not found", id)));
	}

	@Transactional
	public Student saveStudent(Student student) {
		verifyEmail(student.getEmail());
		return studentRepository.save(student);
	}

	@Transactional
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	public Token login(Login login) {

		Student student = studentRepository.findByEmail(login.getUsername()).get();
		if (student == null) {
			throw new RuntimeException("User does not exist.");
		}
		if (!student.getPassword().equals(login.getPassword())) {
			throw new RuntimeException("Password mismatch.");
		}
		return createJWT("cbwa", student.getEmail(), "gabriel");
	}

//	@Override
//	public Course saveCourse(Course course) {
//		//return studentRepository.saveCourse(course);
//	}

	public void verifyEmail(String email) {
		Optional<Student> found = studentRepository.findByEmail(email);
		if (found.isPresent()) {
			throw new InvalidEmailException(String.format("The email %s is already registered", email));
		}
	}

	// creation of token

	private Token createJWT(String id, String subject, String issuer) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
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

	// check user name and password and return a JWT

}
