package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	public Integer getTotalAmountByUserId(Long userId) {
		return transactionRepository.sumAmountByUserId(userId);
	}

	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public Transaction getTransactionById(Long id) {
		return transactionRepository.findById(id).orElse(null);
	}

	public List<Transaction> getTransactionsByUserId(Long userId) {
		return transactionRepository.findByUserId(userId);
	}

	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);
	}
}
