package user;

import database.DataBase;
import model.ATMCash;
import model.Credential;
import model.Transfer;
import model.User;

import java.util.List;
import java.util.Scanner;

public class UserModel {
    static Scanner sc=new Scanner(System.in);
    UserView userView;
    public UserModel(UserView userView) {
    this.userView=userView;
    }

    public User getUser(String mail) {
        List<User> users = DataBase.getInstance().getUsers();
         for(User u:users){
             if(u.getMail().equals(mail)){
                 return u;
             }
         }
         return null;
    }

    public boolean checkCredntails(String mail, String pin) {
        List<Credential> credentials = DataBase.getInstance().getCredentials();
        for(Credential c:credentials){
            if(c.getMail().equals(mail)&&c.getPin().equals(pin)){
                return true;
            }
        }
        return false;
    }

    public void withdrawCash(String mail, int amount) {
        User u = getUser(mail);
        int userAmount= amount;
        if (u.getTotalAmount() > amount) {
            int thousand = 0;
            int fivehundard = 0;
            int hundards=0;
            if (amount <= 10000 && amount > 100) {
                ATMCash atmCash = DataBase.getInstance().getATMCash();
                if(amount<=5000){
                    while(amount>=1000){
                        thousand++;
                        amount=amount-1000;
                        break;
                    }
                    while(amount>=500){
                        fivehundard++;
                        amount=amount-500;
                        if(fivehundard==6){
                            break;
                        }
                    }
                    while(amount>=100){
                        hundards++;
                        amount=amount-100;
                    }
                }
               else if(amount>5000){
                    while(amount>=1000){
                        thousand++;
                        amount=amount-1000;
                        if(thousand==3){
                            break;
                        }
                    }

                    while(amount>=100){
                        hundards++;
                        amount=amount-100;
                        if(hundards==10){
                            break;
                        }
                    }
                    while(amount>=500){
                        fivehundard++;
                        amount=amount-500;
                    }
                }
                System.out.println("thousand " + thousand + " fivehundard " + fivehundard + " hundards " + hundards);
                u.setTotalAmount(u.getTotalAmount() - userAmount);
                        atmCash.setTotalamount(atmCash.getTotalamount() - userAmount);
                        atmCash.setNoofthousands(atmCash.getNoofthousands() - thousand);
                        atmCash.setNoofhundards(atmCash.getNoofhundards() - hundards);
                        atmCash.setNooffivehundards(atmCash.getNooffivehundards() - fivehundard);
                        String remarks="WithDraw Rs. "+userAmount+" from my Account";
                        String transferType="WithDraw";
                        Transfer transfer=new Transfer(u.getAccountNumber(),remarks,transferType,userAmount);
                        u.setTransferList(transfer);
                        System.out.println("you have Successfully Withdraw Rs. " + userAmount + " from your account");
            } else {
                System.out.println("Minimum Amount withdraw would be lesser than 10000 and more than 100");
                userView.init(mail);
            }
        }
        else{
            System.out.println("you are exceed the amount which is in your Account");
            userView.init(mail);
        }
    }

    public User checkAccountNumber(int transferAccountNumber) {
        List<User> users = DataBase.getInstance().getUsers();
        for(User u:users){
            if(u.getAccountNumber()==transferAccountNumber){
              return u;
            }
        }
        return null;
    }

    public void transferAmount(String mail, int transferAccountNumber, int tamount,User transferUser) {
        User u=getUser(mail);
        String transferType1="Debited";
        String transferType2="Credited";
        String transactionRemarks1=tamount+" has been Transfer from your Account to this Account Number "+transferAccountNumber;
        String transactionRemarks2=tamount+" has been Transfer from this Account Number "+u.getAccountNumber()+" to your Account Number "+transferAccountNumber;
        u.setTotalAmount(u.getTotalAmount()-tamount);
        transferUser.setTotalAmount(transferUser.getTotalAmount()+tamount);
        Transfer transfer1 =new Transfer(u.getAccountNumber(),transferAccountNumber,transactionRemarks1,transferType1,tamount);
        Transfer transfer2 =new Transfer(transferAccountNumber,u.getAccountNumber(),transactionRemarks2,transferType2,tamount);
        u.setTransferList(transfer1);
        transferUser.setTransferList(transfer2);
        System.out.println("Transaction completed Successfully");
    }
}
