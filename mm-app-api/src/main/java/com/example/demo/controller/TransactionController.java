package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/sum/{userId}")
	public ResponseEntity<Integer> getTotalAmountByUserId(@PathVariable int userId) {
		try {
			System.out.println("success:GET id:" + userId + " return:"
					+ transactionService.getTotalAmountByUserId(userId));
			int sum = transactionService.getTotalAmountByUserId(userId);
			return ResponseEntity.ok(sum);
		} catch (Exception e) {
			logger.error("Error fetching transaction sum for user ID: " + userId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		try {
			Transaction savedTransaction = transactionService.createTransaction(transaction);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
		} catch (Exception e) {
			logger.error("Error creating transaction", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
		try {
			Transaction transaction = transactionService.getTransactionById(id);
			return ResponseEntity.ok(transaction);
		} catch (Exception e) {
			logger.error("Error fetching transaction by ID: " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable int userId) {
		try {
			List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
			return ResponseEntity.ok(transactions);
		} catch (Exception e) {

			logger.error("Error fetching transactions for user ID: " + userId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
		try {
			transaction.setId(id);
			Transaction updatedTransaction = transactionService.saveTransaction(transaction);
			return ResponseEntity.ok(updatedTransaction);
		} catch (Exception e) {
			logger.error("Error updating transaction", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable int id) {
		try {
			transactionService.deleteTransaction(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error("Error deleting transaction by ID: " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
