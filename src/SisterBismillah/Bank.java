package SisterBismillah;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import SisterBismillah.Database;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import SisterBismillah.Aplikasi;
import SisterBismillah.Account;
import java.sql.ResultSet;

// bank acts as the remote server that the client (ATM) connects to
public class Bank extends UnicastRemoteObject implements BankInterface {
	
        private Database connection;
	private static final long serialVersionUID = -8317765732411101420L;
        
	
	// users accounts
	private static List<Account> accounts = new ArrayList<Account>();
	
	public Bank () throws RemoteException, SQLException{
            super();
            connection = new Database();
            connection.connect();
	}
        
        public int CekLogin(int a,int b) throws SQLException{
        int login = connection.ceklogin(a,b);
        return login;
        }
    
        public int Login(int a,int b) throws SQLException{
        int login = connection.login(a,b);
        return login;
        }
        
        @Override
        public Account ambilAkun(int norek) throws SQLException{
        Account a = connection.getAkun(norek);
        return a;
   }

        @Override
	public double deposit(Account a, double amount) throws RemoteException, SQLException {
		// the deposit function will be called by the remote client (ATM).
		// increase the balance in the account with account number 'accountNum' by 'amount'.
		connection.nabung(a, amount);
                return connection.getAkun(a.getAccountNum()).getBalance();
	}

	@Override
	public double withdraw(Account a, double amount) throws RemoteException, SQLException {
		// the deposit function will be called by the remote client (ATM).
		// increase the balance in the account with account number 'accountNum' by 'amount'.
		connection.narik(a, amount);
                return connection.getAkun(a.getAccountNum()).getBalance();
	}

	@Override
	public double inquiry(Account a) throws RemoteException, SQLException {
		return connection.getAkun(a.getAccountNum()).getBalance();
	}
        
        @Override
        public double transfer(Account a, Account b, double tarik) throws SQLException, RemoteException{
            connection.transfer(a, b, tarik);
            return connection.getAkun(a.getAccountNum()).getBalance();
        }
	
	public static void main(String[] args) throws Exception {
		
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new RMISecurityManager());
//			System.out.println("Security manager set"); 
//		}
		
		@SuppressWarnings("unused")
		Registry registry = LocateRegistry.createRegistry(1106);
		System.setProperty("java.security.policy","file:test.policy");
		
		// Create an instance of the local object 
		BankInterface bankServer = new Bank(); 
		System.out.println("Instance of Bank created"); 
		
		// Put the server object into the Registry
		System.setProperty("java.rmi.server.hostname","192.168.0.103");
		Naming.rebind("rmi://192.168.0.103:1106/BankInterface", bankServer); 
		System.out.println("Name rebind completed"); 
		System.out.println("Server ready for requests!"); 
            Aplikasi model = new Aplikasi();    
            Account a = model.getAkun(1);
            System.out.println(a);
            
            // setup some test accounts with various balances.
            //Account account1 = new Account(100, "Jerry Jones", 10000);
            //Account account2 = new Account(101, "Mary Maloney", 5000);
            //Account account3 = new Account(102, "Ciaran Carey", 540);
            //Account account4 = new Account(103, "Francis Farnan", 1000);
            //Account account5 = new Account(104, "Ursela Upton", 13400);
            //accounts.add(account1);
            //accounts.add(account2);
            //accounts.add(account3);
            //accounts.add(account4);
            //accounts.add(account5);
		
	}
}