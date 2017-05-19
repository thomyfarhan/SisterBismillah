/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisterBismillah;

import java.util.Date;
import java.util.List;

public interface AccountInterface {
	
	public void addTransaction(String type, double amount);
	public List<Transaction> getTransactions();
	public List<Transaction> getTransactionsByDate(Date fromDate, Date toDate);
	public int getAccountNum();
	public void setAccountNum(int accountNum);
	public double getBalance();
	public void setBalance(double balance);
	public String getAccountName();
	public void setAccountName(String accountName);
	
}
