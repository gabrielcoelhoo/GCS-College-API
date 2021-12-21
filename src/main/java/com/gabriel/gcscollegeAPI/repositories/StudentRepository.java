package com.gabriel.gcscollegeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.gcscollegeAPI.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
