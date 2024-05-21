package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
	EXPENSE, INCOME, SUBSCRIPTION;

	@JsonCreator
	public static TransactionType fromValue(String value) {
		return TransactionType.valueOf(value.toUpperCase());
	}

	@JsonValue
	public String toValue() {
		return this.name().toLowerCase();
	}
}