package com.emailoptlogin.serviceimp;

import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emailoptlogin.binding.UserRegistration;
import com.emailoptlogin.entity.User;
import com.emailoptlogin.repository.UserRepository;
import com.emailoptlogin.service.UserService;
import com.emailoptlogin.utils.EmailUtil;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailUtil emailUtil;

	@Override
	public User saveUser(UserRegistration userRegister) {
		User user = new User();
		BeanUtils.copyProperties(userRegister, user);
		return userRepository.save(user);

	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	// method to generate random password
	public String randomPwd() {
		final String characters = "1234567890";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			Random random = new Random();
			sb.append(characters.charAt(random.nextInt(characters.length())));
		}
		return sb.toString();
	}

	@Override
	public String getOtp(String email) {
		User user = userRepository.findByEmail(email);
		user.setOtp(randomPwd());
		
		userRepository.save(user);

		String subject = "Your Otp is Created";
		String body = "here is your OTP (one time password) to verify your identity :" + "<h1>" + user.getOtp()
				+ "<h1/>";

 		return emailUtil.sendMail(subject, body, user.getEmail());
	}

	@Override
	public User userLoginByOtp(String email, String otp) {
		User user = userRepository.findByEmailAndOtp(email,otp);
		return user;
	}
 

	@Override
	public User userLoginByPassword(String mobile, String password) {
		User user = userRepository.findByMobileAndPassword(mobile,password);
		return user;
	}
	
	
}
