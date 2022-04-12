package com.catcafe.game;

import javafx.scene.effect.Light;

public class PointOfSale {
    private Account account;
    private CustomerManager customerManager;
    //private static PointOfSale pos;

//    private PointOfSale(Account accountTracker, CustomerManager manager){
//        account = accountTracker;
//        customerManager = manager;
//    }

    public PointOfSale(){

    }

//    public static PointOfSale getInstance(){
//        return pos;
//    }

    /*
     Compare the customer request and created beverage and see if they are the same
     */
    public void orderUp(Beverage bev){
        Customer c = customerManager.nextCustomer();

    }
}
