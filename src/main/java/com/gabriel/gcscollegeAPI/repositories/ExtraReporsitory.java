package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.Extra;


@Repository
public interface ExtraReporsitory extends JpaRepository<Extra, Long>{

	
}