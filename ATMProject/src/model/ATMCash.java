package model;

public class ATMCash {
    long totalamount;
    int noofthousands;
    int nooffivehundards;
    int noofhundards;

    public long getTotalamount() {
        return totalamount;
    }

    public int getNoofthousands() {
        return noofthousands;
    }

    public int getNooffivehundards() {
        return nooffivehundards;
    }

    public int getNoofhundards() {
        return noofhundards;
    }

    public void setTotalamount(long totalamount) {
        this.totalamount = totalamount;
    }

    public void setNoofthousands(int noofthousands) {
        this.noofthousands = noofthousands;
    }

    public void setNooffivehundards(int nooffivehundards) {
        this.nooffivehundards = nooffivehundards;
    }

    public void setNoofhundards(int noofhundards) {
        this.noofhundards = noofhundards;
    }
}
