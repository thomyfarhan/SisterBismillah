/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisterBismillah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import SisterBismillah.Account;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuzulul Aulia P P
 */
public class Database {
    private String dbuser = "root";
    private String dbpassword = "";
    private Statement statement;
    private Connection connection;
    private ResultSet rs;
    
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/db_bank",dbuser,dbpassword);
        statement = connection.createStatement();
    }
    
    public int ceklogin(int a, int b) throws SQLException{
        ArrayList<Account> listAccount = new ArrayList<>();
        ResultSet rs = null;
        String query1 = "select * from akun where norek='"+a+"' and pin='"+b+"'";
        rs = statement.executeQuery(query1);
        while (rs.next()){
            Account n = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
            listAccount.add(n);
        }
        //boolean b = false;
        int hasil = 0;
            if(listAccount.isEmpty()){
                //b=false;
                hasil = 0;
            }else if(listAccount.get(0).getAccountNum() == a){
                //b=true;
                hasil = 1;
            }
        return hasil;
    }
    
    public int login(int a,int b) throws SQLException{
        ArrayList<Account> listAkun = new ArrayList<>();
        ResultSet rs = null;
        String query1 = "select * from akun where norek='"+a+"'";
        rs = statement.executeQuery(query1);
        while (rs.next()){
            Account n = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3) ,rs.getInt(3));
            listAkun.add(n);
        }
        boolean c = false;
        int hasil = 0;
            if(listAkun.get(0).getAccountPin()!= b){
                c=false;
                hasil = 0;
            }else if(listAkun.get(0).getAccountPin() == b){
                c=true;
                hasil = 1;
            }
            return hasil;
    }
    
    public void saveAkun(Account a) throws SQLException{
        String query = "insert into akun(norek,pin,nama,saldo) values('" +  a.getAccountNum()+ "', '" + a.getAccountPin() + "'," + a.getAccountName()
                        + ", '" + a.getBalance() + "')";
        statement.execute(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.getGeneratedKeys();
    }
    
    public Account getAkun(int norek) throws SQLException{
        Account a = null;
        String query = "SELECT * FROM akun WHERE norek="+norek;
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            a = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));   
        }
        return a;
   }
    
    public void nabung(Account a, double tabung) throws SQLException{
        double tambah=a.getBalance()+tabung;
        try {
            String query= "update akun set saldo= "+tambah+" where norek = "+a.getAccountNum();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void narik(Account a, double tarik) throws SQLException{
        double kurang=a.getBalance()-tarik;
        try {
            String query= "update akun set saldo= "+kurang+" where norek = "+a.getAccountNum();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void transfer(Account a, Account b, double tarik) throws SQLException{
        double kurang=a.getBalance()-tarik;
        double tambah=b.getBalance()+tarik;
        try {
            String query= "update akun set saldo= "+kurang+" where norek = "+a.getAccountNum();
            statement.executeUpdate(query);
            String query1= "update akun set saldo= "+tambah+" where norek = "+b.getAccountNum();
            statement.executeUpdate(query1);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
