package model;

public class Transfer {
    static int nextId=0;
    int transactionId;
    int AccountNumber;
    int transferAccountNumber;
    String transactionRemark;
    String transactionType;
    int transactionAmount;

    public Transfer(int accountNumber, String transactionRemark, String transactionType, int transactionAmount){
        AccountNumber = accountNumber;
        this.transactionRemark = transactionRemark;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionId=nextId++;
    }

    public Transfer(int accountNumber, int transferAccountNumber, String transactionRemark, String transactionType, int transactionAmount) {
        AccountNumber = accountNumber;
        this.transferAccountNumber = transferAccountNumber;
        this.transactionRemark = transactionRemark;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionId=nextId++;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public int getTransferAccountNumber() {
        return transferAccountNumber;
    }

    public String getTransactionRemark() {
        return transactionRemark;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }
}
