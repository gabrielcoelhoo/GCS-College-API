package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
