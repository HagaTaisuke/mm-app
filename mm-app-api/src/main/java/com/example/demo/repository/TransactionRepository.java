package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId")
	Integer findTotalAmountByUserId(@Param("userId") int userId);

	List<Transaction> findByUserId(int userId);

	void deleteAllByUser(User user);
}