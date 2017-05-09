/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SisterBismillah;
import SisterBismillah.Database;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Arfiatnaprambudi
 */
public class Aplikasi {
    private ArrayList<Account> daftarAkun;
    private Database connection;
    
    public Aplikasi() throws SQLException{
        this.daftarAkun = new ArrayList<>();
        this.connection = new Database();
        connection.connect();
    }
    
    
    public int createAkun(int norek, int pin, String nama, double saldo) throws SQLException{
        Account m = new Account(norek,pin,nama,saldo);
        daftarAkun.add(m);
        connection.saveAkun(m);
        return m.getAccountNum();
    }
    
    
    public Account getAkun(int norek) throws SQLException{
        for (Account a : daftarAkun){
            if (a.getAccountNum()==norek){
                return a;
            }
        }
        Account a= connection.getAkun(norek);
        daftarAkun.add(a);
        return a;
    }
    
   public void menabung(Account a, double tabung) throws SQLException{
        connection.nabung(a, tabung);
    }
    
   public void menarik(Account a, double tarik) throws SQLException{
        connection.narik(a, tarik);
    }
}
