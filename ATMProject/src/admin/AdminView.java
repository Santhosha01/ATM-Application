package admin;

import database.DataBase;
import loginview.LoginView;
import model.ATMCash;
import model.Credential;
import model.User;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    static Scanner sc=new Scanner(System.in);
    public void init() {
        System.out.println("------------Welcome to Canara Bank Admin Page-------------");
        System.out.println("1.Load Cash to the ATM \n2.View the amount in the Bank \n3.View Customer Details \n4.Exit");
        System.out.println("enter your choice");
        int choice=sc.nextInt();
       switch (choice){
           case 1:
               loadCash();
               break;
           case 2:
               viewTotalAmount();
               break;
           case 3:
               viewCustomerDetails();
               init();
               break;
           case 4:
               new LoginView().start();
               break;
           default:
               System.out.println("Invalid input");
       }
    }

    private void viewCustomerDetails() {
        List<User> users = DataBase.getInstance().getUsers();
        if(users.isEmpty()) {
            System.out.println("There is no Customer Details");
        }
        else {
            int index = 1;
            for (User u : users) {
                System.out.println("--------Customer " + index++ + " Details---------");
                System.out.println("Account No - " + u.getAccountNumber());
                System.out.println("Account Holder - " + u.getName());
                List<Credential> credentials = DataBase.getInstance().getCredentials();
                for (Credential c : credentials) {
                    if (c.getMail().equals(u.getMail())) {
                        System.out.println("Pin Number of the Account - " + c.getPin());
                        break;
                    }
                }
                System.out.println("Account Balance - " + u.getTotalAmount());
            }
        }
    }

    private void viewTotalAmount() {
        ATMCash cash=DataBase.getInstance().getATMCash();
        System.out.println("Total cash in the ATM Machine is Rs. " + cash.getTotalamount());
        init();
    }

    private void loadCash() {
        System.out.println("Enter the amount you want to add");
        long amount=sc.nextLong();
        sc.nextLine();
        System.out.println("Enter the number of 1000 you want to add");
        int thousand=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the number of 500 you want to add");
        int fivehundard=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the number of 100 you want to add");
        int hundards=sc.nextInt();
        sc.nextLine();
        int sum=1000*thousand+500*fivehundard+100*hundards;
        if(sum==amount){
            DataBase.getInstance().storeATMCash(amount,thousand,fivehundard,hundards);
            System.out.println("Cash Loaded to the ATM Successfully");
        }
        else{
            System.out.println("Invalid amount added");
        }
        init();
    }
}
