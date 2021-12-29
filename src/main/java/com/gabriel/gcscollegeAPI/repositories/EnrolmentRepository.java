package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.Enrolment;


@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Long>{

	
}
