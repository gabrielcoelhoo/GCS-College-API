package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.Role;
import com.gabriel.gcscollegeAPI.model.Student;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Student save(Student student);



}
