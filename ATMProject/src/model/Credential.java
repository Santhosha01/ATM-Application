package model;

public class Credential {
    String mail;
    String pin;

    public String getMail() {
        return mail;
    }

    public String getPin() {
        return pin;
    }

    public Credential(String mail, String pin) {
        this.mail = mail;
        this.pin = pin;
    }
}
