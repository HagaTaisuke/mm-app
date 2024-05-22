package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping //全てのuserを取得
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/{id}") //idからuserを取得
	public User getUserById(@PathVariable int id) {
		User user = userService.getUserById(id);
		return user;
	}

	@PostMapping //userを作成
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping("/{id}") //userを更新
	public User updateUser(@RequestBody User user) {

		return userService.updateUser(user);
	}

	@DeleteMapping("/{id}") //userを削除
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/all") //全てのuserを削除 (use admin)
	public ResponseEntity<Void> deleteAllUsers() {
		userService.deleteAllUsers();
		return ResponseEntity.noContent().build();
	}
}
