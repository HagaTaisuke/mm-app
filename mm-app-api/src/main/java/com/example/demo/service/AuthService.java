package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@Service
public class AuthService {

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	public boolean loginUser(String username, String rawPassword) {
		// データベースからユーザー情報を取得するロジック
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isEmpty()) {
			return false; // ユーザーが見つからない場合
		}
		User user = userOptional.get();
		// 入力されたパスワードとハッシュ化されたパスワードを比較
		return passwordEncoder.matches(rawPassword, user.getPasswordHash());
	}

	public String generateToken(String username) {
		return JwtUtil.generateToken(username);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public boolean isUserExists(String username, String email) {
		return userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent();
	}

	public String hashPassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
