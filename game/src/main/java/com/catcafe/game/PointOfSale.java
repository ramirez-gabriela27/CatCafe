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
     Compare the customer request and created beverage and see if they are the same. Returns true if success.
     */

    public Boolean orderUp(Item bev){
        if(bev instanceof Beverage){
            Customer c = customerManager.nextCustomer();
            Request r = c.getRequest();
            if(r.getRequestedItem().compare(bev)) {
                account.addMoney(((Beverage) bev).getCost());
                customerManager.remove(c);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
