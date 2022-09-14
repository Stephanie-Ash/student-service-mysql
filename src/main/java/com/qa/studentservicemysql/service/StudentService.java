package com.qa.studentservicemysql.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.studentservicemysql.dto.StudentDto;
import com.qa.studentservicemysql.entity.Student;
import com.qa.studentservicemysql.exception.StudentAlreadyExistsException;
import com.qa.studentservicemysql.exception.StudentNotFoundException;
import com.qa.studentservicemysql.repository.StudentRepository;


@Service
public class StudentService implements IStudentService {
	
	@Autowired
	StudentRepository studRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Student saveStudent(Student student) throws StudentAlreadyExistsException {
		Optional<Student> findByEmail = studRepository.findByEmail(student.getEmail());
		if(findByEmail.isPresent()) {
			throw new StudentAlreadyExistsException();
		} else {
			return studRepository.save(student);
		}
	}

	@Override
	public List<Student> getAllStudents() {
		return studRepository.findAll();
	}

	@Override
	public Student getStudentById(int id) throws StudentNotFoundException {
		Optional<Student> findById = studRepository.findById(id);
		
		if(!findById.isPresent()) {
			throw new StudentNotFoundException();
		} else {
			return findById.get();
		}
			
	}

	@Override
	public Student updateStudent(Student student) throws StudentNotFoundException {
		Optional<Student> findById = studRepository.findById(student.getId());
		
		if(!findById.isPresent()) {
			throw new StudentNotFoundException();
		} else {
			Student existingStud = findById.get();
			existingStud.setEmail(student.getEmail());
			return studRepository.saveAndFlush(existingStud);
		}
	}

	@Override
	public boolean deleteStudent(int id) throws StudentNotFoundException {
		boolean status = false;
		Optional<Student> findById = studRepository.findById(id);
		if(!findById.isPresent()) {
			throw new StudentNotFoundException();
		} else {
			studRepository.delete(findById.get());
			status = true;
		}
		
		return status;
	}
	
	private StudentDto mapToDto(Student student) {
		return this.mapper.map(student, StudentDto.class);
	}

	@Override
	public List<StudentDto> getAllStudentDtos() {
		
		return this.studRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

}
