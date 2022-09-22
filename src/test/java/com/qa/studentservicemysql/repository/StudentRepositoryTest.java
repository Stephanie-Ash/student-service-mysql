package com.qa.studentservicemysql.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.studentservicemysql.entity.Student;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studRepository;
	
	private Student john;
	private Student jane;
	private Student jim;
	
	@BeforeEach
	public void setUp() {
		john = new Student(1, "John", "Smith", "john@email.com", 18, "Software Development");
		jane = new Student(1, "Jane", "Doe", "jane@email.com", 19, "Software Development");
		jim = new Student(1, "Jim", "Bob", "jim@email.com", 18, "Software Development");
	}
	
	@AfterEach
	public void tearDown() {
		john = jane = jim = null;
		studRepository.deleteAll();
	}
	
	@Test
	public void test_student_saved() {
		studRepository.save(john);
		Student savedStudent = studRepository.findById(john.getId()).get();
		
		assertNotNull(savedStudent);
		assertEquals("John", savedStudent.getFirstName());
	}
	
	@Test
	public void test_find_all_returns_student_list() {
		studRepository.save(john);
		studRepository.save(jane);
		studRepository.save(jim);
		
		List<Student> studList = studRepository.findAll();
		assertEquals(3, studList.size());
		assertEquals("John", studList.get(0).getFirstName());
	}
	
	@Test
	public void test_find_by_email_returns_student() {
		studRepository.save(john);
		
		Student selectedStudent = studRepository.findByEmail(john.getEmail()).get();
		assertEquals("John", selectedStudent.getFirstName());
	}
	
	@Test
	public void test_find_by_incorrect_id_returns_empty_optional() {
		assertThat(studRepository.findById(1).isEmpty());
	}
	
	@Test
	public void test_find_by_incorrect_email_returns_empty_optional() {
		assertThat(studRepository.findByEmail("email@email.com").isEmpty());
	}

}
