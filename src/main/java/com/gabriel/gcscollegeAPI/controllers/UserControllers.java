package com.gabriel.gcscollegeAPI.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.gcscollegeAPI.config.JwtTokenProvider;
import com.gabriel.gcscollegeAPI.model.Role;
import com.gabriel.gcscollegeAPI.model.User;
import com.gabriel.gcscollegeAPI.repositories.RoleRepository;
import com.gabriel.gcscollegeAPI.repositories.UserRepository;
import com.gabriel.gcscollegeAPI.services.UserServiceImpl;
import com.gabriel.gcscollegeAPI.utils.ConstantUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/students")
public class UserControllers {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserServiceImpl studentService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/{studentID}")
	public User findByID(@PathVariable Long studentID) {
		
		return studentService.findByIDOrThrowsException(studentID);
		
//		if(studentService.findByID(studentID)) {
//			return new ResponseEntity<>(, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>("User with ID = {studentID} not found", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getStudents() {
		return ResponseEntity.ok().body(studentService.findAll());
	}

	@PostMapping("/create")
	public ResponseEntity<User>saveStudent(@RequestBody User user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/students/create").toUriString());
		return ResponseEntity.created(uri).body(studentService.saveStudent(user));

	}
	
//	@PostMapping("/roleSave")
//	public ResponseEntity<Role>saveRole(@RequestBody Role role) {
//		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/students/roleSave").toUriString());
//		return ResponseEntity.created(uri).body(studentService.saveRole(role));
//
//	}
//	
//	@PostMapping("/role/addtouser")
//	public ResponseEntity<?>addRoletoUser(@RequestBody RoleToUserForm form) {
//		studentService.addRoleToUser(form.getUsername(), form.getRoleName());
//		return ResponseEntity.ok().build();
//
//	}
	
	public class RoleToUserForm {
		private String username;
		private String roleName;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

	}
	
	


	@PutMapping("/update/{studentID}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public User update(@RequestBody @Valid User repStudent, @PathVariable Long studentID) {
		User studentBD = studentService.findByIDOrThrowsException(studentID);

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

	
	@DeleteMapping("/delete/{idCourse}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteProduct(@PathVariable Long idCourse) {
		studentService.deleteStudent(idCourse);
		return "this course has been deleted successfully";

	}
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody User user) {
		
		JSONObject jsonObject = new JSONObject();
		try {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setRole(roleRepository.findByName(ConstantUtils.USER.toString()));
			User savedUser = userRepository.saveAndFlush(user);
			jsonObject.put("message", savedUser.getName() + " saved succesfully");
			return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
		} catch (JSONException e) {
			try {
				jsonObject.put("exception", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody User user) {

		JSONObject jsonObject = new JSONObject();
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			if (authentication.isAuthenticated()) {
				String email = user.getEmail();
				jsonObject.put("name", authentication.getName());
				jsonObject.put("authorities", authentication.getAuthorities());
				jsonObject.put("token", tokenProvider.createToken(email, userRepository.findByEmail(email).getRole()));
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} catch (JSONException e) {
			try {
				jsonObject.put("exception", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
		}
		return null;
	}


}
