package com.gabriel.gcscollegeAPI.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.services.StudentServiceImpl;

@RestController
public class UserController {
  
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @PostConstruct
    public void initRoleAndUser() {
    	studentServiceImpl.initRoleAndUser();
    }
    
    //I need to this method cuz in student controller is saving an user as well
    
    @PostMapping({"/registerNewUser"})
    public Student registerNewUser(@RequestBody Student student) {
        return studentServiceImpl.registerNewStudent(student);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
