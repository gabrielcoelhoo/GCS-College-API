package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import com.gabriel.gcscollegeAPI.model.ExtraEnrolment;

public interface ExtraEnrolmentRepository extends JpaRepository<ExtraEnrolment, Long> {

}
=======
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.ExtraEnrolment;


@Repository
public interface ExtraEnrolmentRepository extends JpaRepository<ExtraEnrolment, Long>{

	
}
>>>>>>> 993546fc88a1b5fe520acdb90a2d57303a490c6d
