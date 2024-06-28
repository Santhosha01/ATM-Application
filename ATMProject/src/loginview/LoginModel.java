package loginview;

import admin.AdminView;
import com.sun.tools.javac.Main;
import database.DataBase;
import model.Credential;
import user.UserView;

import java.util.List;

public class LoginModel {
    LoginView loginView;
    AdminView adminView;
    UserView userView;
    public LoginModel(LoginView loginView) {
      adminView=new AdminView();
      userView=new UserView();
      this.loginView=loginView;
    }

    public void checkUser(String mail, String pin) {
        if(mail.equals("canarabank@gmail.com")&&pin.equals("cbank123")){
          adminView.init();
      }
      else if(checkCredential(mail,pin)){
          userView.init(mail);
        }
      else{
            System.out.println("Invalid User Name or Pin Number");
            loginView.start();
        }
    }

    private boolean checkCredential(String mail, String pin) {
        List<Credential> credentialList= DataBase.getInstance().getCredentials();
        for(Credential c:credentialList){
            if(c.getMail().equals(mail)&&c.getPin().equals(pin)){
                return true;
            }
        }
        return false;
    }
}
