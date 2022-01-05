package com.gabriel.gcscollegeAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabriel.gcscollegeAPI.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query("FROM Employee where quantityEnrolments < 10 order by quantityEnrolments ASC")
	List<Employee> findEmployeesAvailable();
}
