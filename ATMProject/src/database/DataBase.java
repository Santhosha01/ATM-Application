package database;

import model.ATMCash;
import model.Credential;
import model.Transfer;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    static DataBase dataBase;
    private List<User> users=new ArrayList<>();
    private List<Credential> credentials=new ArrayList<>();
    private ATMCash cash=new ATMCash();
    private List<Transfer> transfers=new ArrayList<>();
    public static DataBase getInstance(){
      if(dataBase==null){
          dataBase=new DataBase();
      }
      return dataBase;
    }

    public void storeUser(String name, String mail, String phoneNumber, long amount, String pin) {
        User u=new User(name,mail,phoneNumber,amount);
        users.add(u);
        storeCredentails(mail,pin);
    }

    private void storeCredentails(String mail, String pin) {
        Credential c=new Credential(mail,pin);
        credentials.add(c);
    }
    public List<User> getUsers(){
        return users;
    }
    public List<Credential> getCredentials(){
        return credentials;
    }


    public void storeATMCash(long amount, int thousand, int fivehundard, int hundards) {
      cash.setTotalamount(cash.getTotalamount()+amount);
      cash.setNoofthousands(cash.getNoofthousands()+thousand);
      cash.setNooffivehundards(cash.getNooffivehundards()+fivehundard);
      cash.setNoofhundards(cash.getNoofhundards()+hundards);
    }
    public ATMCash getATMCash(){
        return cash;
    }

    public void storeTransactions(Transfer transfer) {
       transfers.add(transfer);
    }
    public List<Transfer> getAllTransfers(){
        return transfers;
    }
}
