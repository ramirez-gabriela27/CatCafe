package com.catcafe.game;

import javafx.scene.effect.Light;

public class PointOfSale {
    private Account account;
    private CustomerManager customerManager;
    private static PointOfSale pos;

    private PointOfSale(Account accountTracker, CustomerManager manager){
        account = accountTracker;
        customerManager = manager;
    }

    public static PointOfSale getInstance(Account bankAccount, CustomerManager manager){
        if (pos == null){
            pos = new PointOfSale(bankAccount, manager);
        }
        return pos;
    }

    private void setAccount(Account givenAccount){
        account = givenAccount;
    }

    /*
     Compare the customer request and created beverage and see if they are the same
     */
    public void orderUp(Beverage bev){
        Customer c = customerManager.nextCustomer();
        Request r = c.getRequest();
        //call beverage compare method
        //boolean drinkMatch = bev.compareTo(r)
        //if true (correct drink) -> can do customer manager. remove or tell it that request is complete?
        //if (drinkMatch){
        // }
        //temp for first sprint
        //bev.getDescription == r.getDescription()
        if(r.){
            customerManager.remove(c);
        }
    }
}
