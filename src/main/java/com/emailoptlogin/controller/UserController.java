package com.emailoptlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emailoptlogin.binding.UserRegistration;
import com.emailoptlogin.entity.User;
import com.emailoptlogin.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/userRegister")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistration userRegister) {

		User saveUser = userService.saveUser(userRegister);
		if (saveUser != null) {
			return new ResponseEntity<String>("user registered successfully!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("something went wrong!!!", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/allUsers")
	public java.util.List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/generateOtp/{email}")
	public String getOtp(@PathVariable String email) {
		return userService.getOtp(email);
	}

	@PostMapping("/loginByOtp")
	public ResponseEntity<String> userLoginByOtp(@RequestParam String email, String otp) {
		User user = userService.userLoginByOtp(email, otp);
		if (user != null) {
			return new ResponseEntity<String>("user login successfully!!!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("wrong credentials!!!", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/loginByPassword")
	public ResponseEntity<String> userLoginByPassword(@RequestParam String mobile, String password) {
		User user = userService.userLoginByPassword(mobile, password);

		if (user != null) {
			return new ResponseEntity<String>("user login successfully!!!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("wrong credentials!!!", HttpStatus.BAD_REQUEST);
		}

	}
}
