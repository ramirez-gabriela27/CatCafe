package com.catcafe.game;

import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers;
    private CatCustomerInteraction catInteraction;

    public CustomerManager(CatManager catManager, Account account){
        customers = new ArrayList<Customer>();
        catInteraction = new CatCustomerInteraction(catManager, account);
    }

    /**
     * A new customer spawns
     */
    public void spawn(){

    }

    /**
     *
     * @param customer custoer to be removed
     *
     * Customers are removed when their request is fulfilled so this method will call the cat interaction
     * first and then actually get rid of the customer
     */
    public void remove(Customer customer){

    }

    /**
     *
     * @return returns next customer in line
     */
    public Customer nextCustomer(){
        return null;
    }

    /**
     * Goes through cats with current requests and checks if it is time to decrement their patience
     */
    public void patienceRoutine(){

    }
}
