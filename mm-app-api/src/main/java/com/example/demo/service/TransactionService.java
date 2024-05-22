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

	//userのトランザクションを全取得
	public List<Transaction> findAllTransactions(int userId) {
		return transactionRepository.findByUserId(userId);
	}

	//トランザクションを保存
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	//トランザクションを削除
	public void deleteById(int id) {
		transactionRepository.deleteById(id);
	}

	//userの全トランザクションの合計金額を取得
	public Integer getTotalAmountByUserId(int userId) {
		Integer result = transactionRepository.getTotalAmountByUserId(userId);
		return result != null ? result : 0;
	}

	//年月からトランザクションを取得
	public List<Transaction> findByMonth(int userId, int month, int year) {
		return transactionRepository.findByUserIdAndMonth(userId, month, year);
	}

	//タイプ毎にトランザクションを取得
	public List<Transaction> findByType(int userId, String type) {
		return transactionRepository.findByUserIdAndType(userId, type);
	}

	//カテゴリ名でトランザクションをあいまい検索
	public List<Transaction> searchByCategoryName(int userId, String category) {
		return transactionRepository.searchByUserIdAndCategoryLike(userId, category);
	}

	//userの全てのトランザクションを削除
	public void deleteUserTransaction(int id) {
		transactionRepository.deleteAllByUserId(id);
	}

}
