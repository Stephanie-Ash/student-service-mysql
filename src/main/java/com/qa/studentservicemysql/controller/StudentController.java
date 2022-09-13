package com.qa.studentservicemysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.studentservicemysql.entity.Student;
import com.qa.studentservicemysql.exception.StudentAlreadyExistsException;
import com.qa.studentservicemysql.exception.StudentNotFoundException;
import com.qa.studentservicemysql.service.StudentService;



@RestController
@RequestMapping("api/v1/student-service")
public class StudentController {
	
	@Autowired
	StudentService studService;
	
	ResponseEntity<?> responseEntity;
	
	@PostMapping("/add")
	public ResponseEntity<?> saveStudent(@RequestBody Student student) throws StudentAlreadyExistsException {
		Student createdStudent;
		try {
			createdStudent = this.studService.saveStudent(student);
		} catch (StudentAlreadyExistsException e) {
			throw e;
		}
		responseEntity = new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/students")
	public ResponseEntity<?> getAllEmployees() {
		return new ResponseEntity<>(this.studService.getAllStudents(), HttpStatus.OK);
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") int id) throws StudentNotFoundException {
		Student student;
		try {
			student = this.studService.getStudentById(id);
		} catch(StudentNotFoundException e) {
			throw e;
		}
		responseEntity = new ResponseEntity<>(student, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/student")
	public ResponseEntity<?> deleteStudent(@RequestParam("id") int id) throws StudentNotFoundException {
		boolean status;
		try {
			status = this.studService.deleteStudent(id);
			responseEntity = new ResponseEntity<>("Student deleted", HttpStatus.OK);
		} catch(StudentNotFoundException e) {
			throw e;
		}
		
		return responseEntity;
	}
	
	@PutMapping("/student")
	public ResponseEntity<?> updateStudent(@RequestBody Student student) throws StudentNotFoundException {
		try {
			responseEntity = new ResponseEntity<>(studService.updateStudent(student), HttpStatus.OK);
		} catch(StudentNotFoundException e) {
			throw e;
		}
		
		return responseEntity;
	}

}
