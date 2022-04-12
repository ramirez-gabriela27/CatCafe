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
    }

    public void removeMoney(Double amountToRemove){
        amount -= amountToRemove;
    }
}
