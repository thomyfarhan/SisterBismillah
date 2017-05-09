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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements AccountInterface, Serializable {
	
	private int norek;
	private String nama;
        private int pin;
	private double saldo;
	private List<Transaction> transactions; // transactions associated with this account object
	
	// constructor for type Account
	public Account (int norek, int pin,String nama, double saldo) {
		this.setAccountNum(norek);
		this.setAccountName(nama);
		this.setBalance(saldo);
		transactions = new ArrayList<Transaction>();
	}
        
        public Account (int norek, int pin, int saldo) {
		this.setAccountNum(norek);
		this.setAccountPin(pin);
		this.setBalance(saldo);
		transactions = new ArrayList<Transaction>();
	}
	
	// add an object of type transaction to the list of transactions
	public void addTransaction(String type, double amount) {
		Transaction e = new Transaction(type, amount, getBalance()); 
		transactions.add(e);
	}
	
	// return all transactions 
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	// return all transactions within a specified date range
	public List<Transaction> getTransactionsByDate(Date fromDate, Date toDate) {
		
		List<Transaction> statementList = new ArrayList<Transaction>();
		
		for (int i=0; i<transactions.size(); i++) {
			Transaction element = transactions.get(i);
			
			// check if the date value falls between the specified range 
			if (element.getTransactionDate().after(fromDate) && element.getTransactionDate().before(toDate)) {
				statementList.add(element); 
			}
		}
		return statementList;
	}

	public int getAccountNum() {
		return norek;
	}

	public void setAccountNum(int accountNum) {
		this.norek = accountNum;
	}
        
        public int getAccountPin() {
		return pin;
	}

	public void setAccountPin(int pin) {
		this.pin = pin;
	}


	public double getBalance() {
		return saldo;
	}

	public void setBalance(double balance) {
		this.saldo = balance;
	}

	public String getAccountName() {
		return nama;
	}

	public void setAccountName(String accountName) {
		this.nama = accountName;
	}
        
        @Override
        public String toString(){
        return "\n norek = " + norek
                + "\n nopin = " + pin
                + "\n nama = " + nama
                + "\n saldo = "+ saldo;
    }
}