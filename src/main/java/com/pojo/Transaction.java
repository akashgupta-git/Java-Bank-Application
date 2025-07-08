package com.pojo;

import java.sql.Timestamp;

public class Transaction {
	private int id;
	private int customerId;
	private String type;
	private int amount;
	private Timestamp timestamp;

	public Transaction(int id, int customerId, String type, int amount, Timestamp timestamp) {
		this.id = id;
		this.customerId = customerId;
		this.type = type;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
}
