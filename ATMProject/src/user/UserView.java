package user;

import database.DataBase;
import loginview.LoginView;
import model.ATMCash;
import model.Transfer;
import model.User;

import java.util.List;
import java.util.Scanner;

public class UserView {
    static Scanner sc = new Scanner(System.in);
    UserModel userModel;

    public UserView() {
        userModel = new UserModel(this);
    }

    public void storeUser(String name, String mail, String phoneNumber, long amount, String pin) {
        DataBase.getInstance().storeUser(name, mail, phoneNumber, amount, pin);
        System.out.println("Account Created Successfully");
    }

    public void init(String mail) {
        System.out.println("------------Welcome to Canara Bank User Page---------------");
        System.out.println("1.WithDraw Cash \n2.check Balance \n3.Transfer Money \n4.Mini Statement \n5.Logout");
        System.out.println("Enter your Choice");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                withdrawCash(mail);
                init(mail);
                break;
            case 2:
                checkBalance(mail);
                init(mail);
                break;
            case 3:
                transferMoney(mail);
                init(mail);
                break;
            case 4:
                miniStatement(mail);
                init(mail);
                break;
            case 5:
                new LoginView().start();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    private void miniStatement(String mail) {
        User u = userModel.getUser(mail);
        List<Transfer> transferList = u.getTransferList();
        System.out.println("----------your Account Details-----------");
        System.out.println("Account Number - " + u.getAccountNumber());
        System.out.println("Account Holder - " + u.getName());
        System.out.println("Account Balance - "+u.getTotalAmount());
        System.out.println("--------------Last 5 Transaction History--------");
        if(transferList.isEmpty()){
            System.out.println("No Transaction History");
        }
        else {
            if(transferList.size()>5){
                for(int i=transferList.size()-5;i<transferList.size();i++){
                    System.out.println("Transaction Id - "+transferList.get(i).getTransactionId());
                    System.out.println("Transaction Remarks - "+transferList.get(i).getTransactionRemark());
                    System.out.println("Transaction Type - "+transferList.get(i).getTransactionType());
                    System.out.println("Transaction Amount - "+transferList.get(i).getTransactionAmount());
                }
            }
            else{
                for (int i = 0; i <transferList.size(); i++) {
                    System.out.println("Transaction Id - "+transferList.get(i).getTransactionId());
                    System.out.println("Transaction Remarks - "+transferList.get(i).getTransactionRemark());
                    System.out.println("Transaction Type - "+transferList.get(i).getTransactionType());
                    System.out.println("Transaction Amount - "+transferList.get(i).getTransactionAmount());
                }
            }
        }
    }

    private void transferMoney(String mail) {
        System.out.println("Enter the Account number you want to transfer");
        int transferAccountNumber=sc.nextInt();
        sc.nextLine();
        User transferUser=userModel.checkAccountNumber(transferAccountNumber);
        if(transferUser!=null){
            System.out.println("Account Holder for the Account number you have entered - "+transferUser.getName());
            System.out.println("Do you want to transfer the amount to this User? yes/no");
            String choice=sc.nextLine();
            if(choice.equals("yes")) {
                System.out.println("Enter the amount you want to transfer");
                int tamount = sc.nextInt();
                sc.nextLine();
                if(tamount<=10000&&tamount>1000) {
                    User u = userModel.getUser(mail);
                    if (tamount < u.getTotalAmount()) {
                        userModel.transferAmount(mail, transferAccountNumber, tamount, transferUser);
                    } else {
                        System.out.println("you don't have that much of money in your account");
                    }
                }
                else{
                    System.out.println("you have exceed the Transaction Limit or your amount is below the Transaction Limit");
                }
            }
            else{
                init(mail);
            }
        }
        else{
            System.out.println("User Account Number Not Found");
            init(mail);
        }

    }

    private void checkBalance(String mail) {
        User u = userModel.getUser(mail);
        System.out.println("Your Account balance is " + u.getTotalAmount());
        init(mail);
    }

    private void withdrawCash(String mail) {
        System.out.println("Enter your PIN Number");
        String pin = sc.nextLine();
        if (userModel.checkCredntails(mail, pin)) {
            System.out.println("Enter the amount you want to withdraw");
            int amount = sc.nextInt();
            sc.nextLine();
            ATMCash atmCash = DataBase.getInstance().getATMCash();
            if(atmCash.getTotalamount()>amount) {
                userModel.withdrawCash(mail, amount);
            }
            else{
                System.out.println("There is No Cash in the ATM Machine");
                init(mail);
            }
        } else {
            System.out.println("Invalid Pin Number");
            init(mail);
        }
    }
}