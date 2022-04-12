package com.catcafe.game;

import java.time.Instant;
import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers;
    private CatCustomerInteraction catInteraction;
    private int customerPatienceThreshold = 10;
    private final int MAX_CUSTOMERS = 4;

    public CustomerManager(CatManager catManager, Account account){
        customers = new ArrayList<Customer>();
        catInteraction = new CatCustomerInteraction(catManager, account);
    }

    /**
     * A new customer spawns
     */
    public void spawn(){
        //If there are already MAX CUSTOMERS dont spawn
        if(customers.size() >= MAX_CUSTOMERS){
            return;
        }
        else {
            Customer newCustomer = new Customer(customerPatienceThreshold);
            customers.add(newCustomer);
            //TODO: Put the customer in line and have the graphics appear
        }
    }

    /**
     *
     * @param customer customer to be removed
     *
     * Customers are removed when their request is fulfilled so this method will call the cat interaction
     * first and then actually get rid of the customer
     */
    public void remove(Customer customer){
        //TODO: Cat interaction
        customers.remove(customer);
        //TODO: Move the other customers up in line in the view
    }

    /**
     *
     * @return returns next customer in line
     */
    public Customer nextCustomer(){
        return customers.get(0);

    }

    /**
     * Goes through cats with current requests and checks if it is time to decrement their patience
     */
    public void patienceRoutine(){
        for(Customer customer: customers){
            if(Instant.now().getEpochSecond() > customer.getNextDecrementTime()){
                customer.decreasePatience();
                customer.setNextDecrementTime();
            }
        }

    }
}
