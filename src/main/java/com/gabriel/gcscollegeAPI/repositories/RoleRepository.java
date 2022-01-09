package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.gcscollegeAPI.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("FROM Role WHERE name=:name")
	Role findByName(@Param("name") String name);

}
