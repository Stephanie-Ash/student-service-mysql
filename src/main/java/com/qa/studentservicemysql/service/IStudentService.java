package com.qa.studentservicemysql.service;

import java.util.List;

import com.qa.studentservicemysql.entity.Student;
import com.qa.studentservicemysql.exception.StudentAlreadyExistsException;
import com.qa.studentservicemysql.exception.StudentNotFoundException;


public interface IStudentService {
	public Student saveStudent(Student student) throws StudentAlreadyExistsException;
	public List<Student> getAllStudents();
	public Student getStudentById(int id) throws StudentNotFoundException;
	public Student updateStudent(Student student) throws StudentNotFoundException;
	public boolean deleteStudent(int id) throws StudentNotFoundException;

}
