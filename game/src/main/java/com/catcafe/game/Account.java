package com.catcafe.game;
//Singleton Pattern, Eager Instantiation

public class Account {
    private Double amount;
    private static Account account = new Account();

    private Account(){
        amount = 0.0;
    }

    public static Account getInstance(){
        return account;
    }

    public void addMoney(Double amountToAdd){
        amount += amountToAdd;
        System.out.println("Adding $" + amountToAdd +". Amount in bank: $" + getAmount());
    }

    public void removeMoney(Double amountToRemove){
        amount -= amountToRemove;
        System.out.println("Adding $" + amountToRemove +". Amount in bank: $" + getAmount());
    }
    public double getAmount(){return amount;}
    public String getAmountString(){
        return String.format("%.2f", amount);
    }
}
