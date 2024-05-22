package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	//userのトランザクションを全取得
	List<Transaction> findByUserId(int userId);

	//userの全トランザクションの合計金額を取得
	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId")
	Integer getTotalAmountByUserId(@Param("userId") int userId);

	//年月からトランザクションを取得
	@Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND MONTH(t.date) = :month AND YEAR(t.date) = :year")
	List<Transaction> findByUserIdAndMonth(@Param("userId") int userId, @Param("month") int month,
			@Param("year") int year);

	//タイプ毎にトランザクションを取得
	List<Transaction> findByUserIdAndType(int userId, String type);

	//カテゴリ名でトランザクションをあいまい検索
	@Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.category LIKE %:category%")
	List<Transaction> searchByUserIdAndCategoryLike(@Param("userId") int userId,
			@Param("category") String category);

	//user削除に際して全てのデータを削除
	void deleteAllByUserId(int userId);
}