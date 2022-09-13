package com.qa.studentservicemysql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.studentservicemysql.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Optional<Student> findByEmail(String email);

}
