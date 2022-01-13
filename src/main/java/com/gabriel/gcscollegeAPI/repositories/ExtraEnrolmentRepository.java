package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.ExtraEnrolment;


@Repository
public interface ExtraEnrolmentRepository extends JpaRepository<ExtraEnrolment, Long>{
	
}
