package com.catcafe.game;
//Singleton Pattern, Lazy Instantiation
import java.time.Instant;
import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers;
    private int customerPatienceThreshold = 10;
    private final int MAX_CUSTOMERS = 4;
    private static CustomerManager customerManager;

    private CustomerManager(Account account){
        customers = new ArrayList<Customer>();
    }

    public static synchronized CustomerManager getInstance(Account account){
        if (customerManager == null){
            customerManager = new CustomerManager(account);
        }
        return customerManager;
    }

    /**
     * A new customer spawns
     */
    public void spawn(){
        //If there are already MAX CUSTOMERS don't spawn
        if(customers.size() >= MAX_CUSTOMERS){
            return;
        }
        else {
            Customer newCustomer = new Customer();
            customers.add(newCustomer);
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
        customer.destroy();
        customers.remove(customer);
    }

    /**
     *
     * @return returns next customer in line
     */
    public Customer nextCustomer(){
        if(customers.size() > 0){
            return customers.get(0);
        }
       else{
           return null;
        }

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

    public void resetManager(){
        customers = new ArrayList<Customer>();
    }
}
