package com.qa.studentservicemysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "student_details")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stud_id")
	private int id;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid name, must only contain letters")
	@Column(name = "stud_fname")
	private String firstName;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid name, must only contain letters")
	@Column(name = "stud_surname")
	private String surname;
	
	@NotNull
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email address is invalid")
	@Column(name = "stud_email")
	private String email;
	
	@NotNull
	@Column(name = "stud_age")
	private int age;
	
	@NotNull
	@Column(name = "stud_subject")
	private String subject;

}
