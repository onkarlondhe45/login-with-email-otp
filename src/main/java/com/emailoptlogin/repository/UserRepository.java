package com.emailoptlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emailoptlogin.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByEmailAndOtp(String email, String otp);

	User findByMobileAndPassword(String mobile, String password);
}
