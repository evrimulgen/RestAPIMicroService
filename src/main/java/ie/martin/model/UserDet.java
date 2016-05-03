package ie.martin.model;

import ie.martin.model.Share;

import java.util.ArrayList;

public class UserDet {

    private String name;

    private double balance;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private ArrayList<PurchasedShare> stocks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<PurchasedShare> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<PurchasedShare> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", balance=" + balance + ", stocks=" + stocks + "]";
    }


}
