package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	public Integer getTotalAmountByUserId(int userId) {
		try {
			Integer result = transactionRepository.getTotalAmountByUserId(userId);

			return result != null ? result : 0;
		} catch (Exception e) {
			logger.error("Error occurred while fetching total amount for user ID: " + userId, e);
			return 0;
		}
	}

	public Transaction createTransaction(Transaction transaction) {
		try {
			return transactionRepository.save(transaction);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create transaction", e);
		}
	}

	public Transaction getTransactionById(int id) {
		try {
			return transactionRepository.findById(id).orElse(null);
		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch transaction by ID: " + id, e);
		}
	}

	public List<Transaction> getTransactionsByUserId(int userId) {
		try {
			return transactionRepository.findByUserId(userId);
		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch transactions for user ID: " + userId, e);
		}
	}

	public Transaction saveTransaction(Transaction transaction) {
		try {
			return transactionRepository.save(transaction);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save transaction", e);
		}
	}

	public void deleteTransaction(int id) {
		try {
			transactionRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete transaction by ID: " + id, e);
		}
	}
}
