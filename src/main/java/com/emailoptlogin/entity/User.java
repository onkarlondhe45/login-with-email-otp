package com.emailoptlogin.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
 	private String name;
 	private String email;
 	private String mobile;
 	private String password;
 	private String otp;
	
	@CurrentTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp 
	private LocalDate updateDate;
}
