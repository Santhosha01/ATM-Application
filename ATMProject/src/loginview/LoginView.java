package loginview;

import user.UserView;

import java.util.Scanner;

public class LoginView {
    LoginModel loginModel;
    UserView userView;
    static Scanner sc=new Scanner(System.in);
    public LoginView(){
        loginModel=new LoginModel(this);
        userView=new UserView();
    }
    public void start() {
        System.out.println("-----------Welcome to Canara Bank------------");
        System.out.println("1.Sign in \n2.Sign Up \n3.Exit");
        System.out.println("Enter your choice");
        int choice=sc.nextInt();
        sc.nextLine();
        switch (choice){
            case 1:
                signIn();
                break;
            case 2:
                signUp();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid Input");
        }
    }

    public void signIn() {
        System.out.println("------------Welcome to Sign In page----------");
        System.out.println("Enter your Mail Id");
        String mail=sc.nextLine();
        System.out.println("Enter your PIN");
        String pin=sc.nextLine();
        loginModel.checkUser(mail,pin);
    }

    public void signUp() {
        System.out.println("------------Welcome to Sign Up page------------");
        System.out.println("Enter your Name");
        String name=sc.nextLine();
        System.out.println("Enter your mail Id");
        String mail=sc.nextLine();
        System.out.println("Enter your phone Number");
        String phoneNumber=sc.nextLine();
        System.out.println("Enter your cash amount in the bank");
        long amount=sc.nextLong();
        sc.nextLine();
        System.out.println("Enter your ATM pin number");
        String pin=sc.nextLine();
        userView.storeUser(name,mail,phoneNumber,amount,pin);
        start();
    }
}
