package com.qa.studentservicemysql.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.qa.studentservicemysql.entity.Student;
import com.qa.studentservicemysql.exception.StudentAlreadyExistsException;
import com.qa.studentservicemysql.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	@Mock
	private StudentRepository studRepository;
	
	@Autowired
	@InjectMocks
	private StudentService studService;
	
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
	}
	
	@Test
	public void test_save_returns_a_student() throws StudentAlreadyExistsException {
		Mockito.when(studRepository.findByEmail(any())).thenReturn(Optional.empty());
		Mockito.when(studRepository.save(any())).thenReturn(john);
		
		Student savedStudent = studService.saveStudent(john);
		assertEquals(savedStudent, john);
		Mockito.verify(studRepository, Mockito.times(1)).findByEmail(john.getEmail());
		Mockito.verify(studRepository, Mockito.times(1)).save(john);
	}
	
	@Test
	public void test_save_with_existing_email_throws_exception() throws StudentAlreadyExistsException {
		Mockito.when(studRepository.findByEmail(john.getEmail())).thenReturn(Optional.of(john));
		
		assertThrows(StudentAlreadyExistsException.class, ()-> studService.saveStudent(john));
	}

}
