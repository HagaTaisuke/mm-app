package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/sum/{userId}")
	public ResponseEntity<Integer> getSumAmountByUserId(@PathVariable Long userId) {
		Integer sumAmount = transactionService.getTotalAmountByUserId(userId);
		return ResponseEntity.ok(sumAmount != null ? sumAmount : 0);
	}

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		Transaction savedTransaction = transactionService.createTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
	}

	@GetMapping("/{id}")
	public Transaction getTransactionById(@PathVariable Long id) {
		return transactionService.getTransactionById(id);
	}

	@GetMapping("/user/{userId}")
	public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
		return transactionService.getTransactionsByUserId(userId);
	}

	@PutMapping("/{id}")
	public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
		transaction.setId(id);
		return transactionService.saveTransaction(transaction);
	}

	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable Long id) {
		transactionService.deleteTransaction(id);
	}
}
