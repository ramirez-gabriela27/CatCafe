package com.catcafe.game;
//Singleton Pattern, Eager Instantiation

public class Account {
    private Double amount;
    private static Account account = new Account();

    private Account(){

    }

    public static Account getInstance(){
        return account;
    }

    public void addMoney(Double amountToAdd){

    }

    public void removeMoney(Double amountToRemove){

    }
    public double getAmount(){return amount;}
}
