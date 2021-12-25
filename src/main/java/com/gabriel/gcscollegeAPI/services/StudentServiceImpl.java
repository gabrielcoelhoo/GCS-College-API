package com.gabriel.gcscollegeAPI.services;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.model.Login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;
import com.gabriel.gcscollegeAPI.model.Token;


@Service
public class StudentServiceImpl implements StudentService{
	
private String SECRET_KEY = "secret";
	
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
	public Token login(Login login) {
		
		Student student = studentRepository.findByUsername(login.getUsername());
		if(student == null) {
			throw new RuntimeException("User does not exist.");
		}
		if(!student.getPassword().equals(login.getPassword())) {
			throw new RuntimeException("Password mismatch.");
		}
		return createJWT("cbwa", student.getUsername(), "gabriel");
	}


		
//		studentRepository.finfindbyemailandid(
//				return createJWT("cbwa", user.getUsername(), "david");
//		}else
//			throw new RuntimeException("User not found");
			
	
	
	

}
