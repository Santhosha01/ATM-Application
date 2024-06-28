package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    static int nextId=1;
    int accountNumber;
    String name;
    String mail;
    String phoneNumber;
    long totalAmount;
    List<Transfer> transferList=new ArrayList<>();

    public User(String name, String mail, String phoneNumber, long totalAmount) {
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.totalAmount = totalAmount;
        this.accountNumber=nextId++;
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

    public void setTransferList(Transfer transfer) {
        transferList.add(transfer);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
