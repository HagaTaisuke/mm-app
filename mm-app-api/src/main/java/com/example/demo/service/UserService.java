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

	//全てのuserを取得
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	//idからuserを取得
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	//userを作成
	public User createUser(User user) {
		return userRepository.save(user);
	}

	//userを更新
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	//userを削除
	public void deleteUser(int id) {
		Optional<User> user = userRepository.findById(id);

		if (user == null) {
			throw new RuntimeException("User not found with id " + id);
		} else {
			transactionRepository.deleteAllByUserId(id);
			userRepository.deleteById(id);
		}
	}

	public void deleteAllUsers() {
		userRepository.deleteAll();
	}
}
