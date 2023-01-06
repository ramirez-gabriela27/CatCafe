package com.catcafe.game;
//Singleton Pattern, Lazy Instantiation
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

    /*
     Compare the customer request and created beverage and see if they are the same. Returns true if success.
     */
    public Double orderUp(Item bev){
        Customer c = customerManager.nextCustomer();
        if(c == null){
            return -1.0;
        }
        if(bev instanceof Beverage){
            System.out.println("Serving Customer " + c.objectID);
            Request r = c.getRequest();
            System.out.println("customer wants " + r.getRequestedItem().getDescription());
            System.out.println("you have " +bev.getDescription());
            if(r.getRequestedItem().compare(bev)) {
                double amount = ((Beverage) bev).getCost();
                account.addMoney(amount);
                customerManager.remove(c);
                System.out.println("Correct Order");
                //TIPPING based on customer patience
                tip(c.patienceLevel);
                return amount;
            }
            else{
                return -1.0;
            }
        }
        else{
            return -1.0;
        }
    }
    public double subtractThrowAway(Item bev){
        if(bev == null){
            return -1;
        }
        else if(bev instanceof Beverage){
            double moneyChange = ((Beverage) bev).getCost() * 0.5;
            account.removeMoney(moneyChange);
            return moneyChange;
        }
        else{
            return -1;
        }
    }
    private void tip(Double patienceLevel){
        account.addMoney(patienceLevel);
        System.out.println("Customer Tipped $" + patienceLevel);
    }
}
