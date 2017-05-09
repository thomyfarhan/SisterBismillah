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

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

public interface BankInterface extends Remote {
	
	public double deposit(Account a, double amount) throws SQLException, RemoteException;
	public double withdraw(Account a, double amount) throws SQLException, RemoteException;
	public double inquiry(Account a) throws SQLException, RemoteException;
        public double transfer(Account a, Account b, double tarik) throws SQLException, RemoteException;
        public Account ambilAkun(int norek) throws SQLException, RemoteException;
   
	
}
