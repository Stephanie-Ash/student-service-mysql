package com.qa.studentservicemysql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Student with this email already exists")
public class StudentAlreadyExistsException extends Exception {

}
