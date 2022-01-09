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
 
//	private String SECRET_KEY = "secret";	
	

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


	// creation of token

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

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByIDOrThrowsException(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The Student of id %d was not found", id)));
	}
//
//	@Override
//	public Role saveRole(Role role) {
//		
//		return roleRepository.save(role);
//	}


	public User saveOrUpdate(User user) {
		return userRepository.saveAndFlush(user);
	}

//	@Override
//	@Transactional
//	public void addRoleToUser(String username, String roleName) {
//		User user = userRepository.findByUsername(username);
//		Role role = roleRepository.findByName(roleName);
//		user.getRoles().add(role);
//		
//	}

//	@Override
//	public User getStudent(String username) {
//		return userRepository.findByUsername(username);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void addRoleToUser(String username, String roleName) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUsername(username);
//		if(user == null) {
//			throw new UsernameNotFoundException("user not found in the database");
//		}else {
//			
//		}
//		
//		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		user.getRoles().forEach(role -> {
//			authorities.add(new SimpleGrantedAuthority(role.getName()));
//		});
//		
//		
//		
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//	}
//	
//	Collection<GrantedAuthority> authorities = new ArrayList<>();
//
//	public Collection<GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	public void setAuthorities(Collection<GrantedAuthority> authorities) {
//		this.authorities = authorities;
//	}
	

	


}