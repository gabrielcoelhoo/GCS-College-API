package com.gabriel.gcscollegeAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;  // main one

import com.gabriel.gcscollegeAPI.model.Student;
import com.gabriel.gcscollegeAPI.repositories.StudentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StudentRepositoryTests {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setCountry("brasil");
		student.setEmail("brasil@gmail.com");
		student.setName("gabriel");
		student.setPassword("gabriel2");
		student.setPhoneNumber("1234567");
		student.setStudentComments("this school is awesome");
		student.setSurname("coelho");
		
		    Student savedStudent  = studentRepository.save(student);
		
		  Student existStudent = entityManager.find(Student.class, savedStudent.getId());
		     
		    assertThat(student.getEmail()).isEqualTo(existStudent.getEmail());
	}
	

}
