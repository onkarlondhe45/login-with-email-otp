package com.emailoptlogin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emailoptlogin.binding.UserRegistration;
import com.emailoptlogin.entity.User;

@Service
public interface UserService {

	User saveUser(UserRegistration userRegister);

	List<User> getAllUser();

	String getOtp(String email);

	User userLoginByOtp(String email, String otp);
	
	User userLoginByPassword(String mobile, String password);

}
