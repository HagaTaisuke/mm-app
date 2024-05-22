package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/api/transactions/{userId}")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	//userのトランザクションを全取得
	@GetMapping
	public List<Transaction> findAllTransactions(@PathVariable int userId) {
		return transactionService.findAllTransactions(userId);
	}

	//トランザクションを保存
	@PostMapping
	public Transaction updateTransaction(@RequestBody Transaction transaction) {
		return transactionService.saveTransaction(transaction);
	}

	//トランザクションを削除
	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable int id) {
		transactionService.deleteById(id);
	}

	@GetMapping("/sum") //userの全トランザクションの合計金額を取得
	public int getTotalAmountByUserId(@PathVariable int userId) {
		return transactionService.getTotalAmountByUserId(userId);
	}

	// 年月からトランザクションを取得
	@GetMapping("/date/{month}/{year}")
	public List<Transaction> findByMonth(@PathVariable int userId, @PathVariable int month, @PathVariable int year) {
		return transactionService.findByMonth(userId, month, year);
	}

	// タイプ毎にトランザクションを取得
	@GetMapping("/type/{type}")
	public List<Transaction> findByType(@PathVariable int userId, @PathVariable String type) {
		return transactionService.findByType(userId, type);
	}

	// カテゴリ名でトランザクションをあいまい検索
	@GetMapping("/category/{category}")
	public List<Transaction> searchByCategoryName(@PathVariable int userId, @PathVariable String category) {
		return transactionService.searchByCategoryName(userId, category);
	}
}
