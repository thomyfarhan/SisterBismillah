/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisterBismillah;
/* 
Author1: Cillian Myles - 11424252 - 4BP1
Author2: Alan Byrne - 10300719 - 4BP1
Module: CT414 - Distributed Systems & Co-op Computing
Assignment: Distributed Banking System
*/

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class Transaction implements Serializable {

	private static final long serialVersionUID = -6841131027488692403L;
	
	// decimal formatting to 2 decimal places
	private DecimalFormat precision2 = new DecimalFormat("0.00");
	
	private String transactionType;
	private double transactionAmount;
	private double upToDateBalance;
	private Date transactionDate;
	
	public Transaction(String transactionType, double transactionAmount, double upToDateBalance){
		this.setTransactionType(transactionType);
		this.setTransactionAmount(transactionAmount);
		this.setUpToDateBalance(upToDateBalance);
		transactionDate = new Date();
	}
	
	public String toString() {
		return "Type: " + transactionType +
				"\nAmount: €" + precision2.format(transactionAmount) +
			    "\nBalance: €" + precision2.format(upToDateBalance) +
			    "\nDate: " + transactionDate.toString() + "\n";
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public double getUpToDateBalance() {
		return upToDateBalance;
	}

	public void setUpToDateBalance(double upToDateBalance) {
		this.upToDateBalance = upToDateBalance;
	}
}
