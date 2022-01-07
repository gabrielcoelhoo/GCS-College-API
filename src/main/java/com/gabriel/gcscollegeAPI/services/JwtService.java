package com.gabriel.gcscollegeAPI.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollege.util.JwtUtil;
import com.gabriel.gcscollegeAPI.model.JwtRequest;
import com.gabriel.gcscollegeAPI.model.JwtResponse;
import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private Student student;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userEmail = jwtRequest.getUserEmail();
        String userPassword = jwtRequest.getUserPasword();
        authenticate(userEmail, userPassword);

        UserDetails userDetails = loadUserByUsername(userEmail);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Student student = studentRepository.findById(userEmail).get();
        return new JwtResponse(student, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(student) throws UsernameNotFoundException {
    	
    	studentRepository.findByEmail(student);

        if (student != null) {
            return new org.springframework.security.core.userdetails.User(
            		student,
            		student.getUserPassword(),
                    getAuthority(student)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(Student student) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        student.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userEmail, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
