package com.qa.studentservicemysql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Student doesn't exist")
public class StudentNotFoundException extends Exception {

}
