package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(Long id, User userDetails) {
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setUsername(userDetails.getUsername());
			user.setPasswordHash(userDetails.getPasswordHash());
			user.setEmail(userDetails.getEmail());
			return userRepository.save(user);
		} else {
			throw new RuntimeException("User not found with id " + id);
		}
	}

	public void deleteUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			transactionRepository.deleteAllByUser(user);
			userRepository.deleteById(id);
		} else {
			throw new RuntimeException("User not found with id " + id);
		}
	}

	public void deleteAllUsers() {
		userRepository.deleteAll();
	}
}
