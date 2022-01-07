package com.gabriel.gcscollegeAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.model.Role;
import com.gabriel.gcscollegeAPI.repositories.RoleRepository;

@Service
public class RoleService {


    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }

}
