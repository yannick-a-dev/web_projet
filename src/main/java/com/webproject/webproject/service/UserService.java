package com.webproject.webproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webproject.webproject.dao.UserRepository;
import com.webproject.webproject.model.User;

@Service
public class UserService {

	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateOneUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}else {
			return null;
		}
	}

	public void deleteById(Long userId) {
	  userRepository.deleteById(userId);
		
	}	
	
}
