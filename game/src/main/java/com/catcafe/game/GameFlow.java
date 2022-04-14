package com.catcafe.game;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import java.time.Instant;

public class GameFlow {
    //All of these times will be Unix Timestamps (seconds since 1970) which is a long.
    //https://attacomsian.com/blog/java-get-unix-timestamp
    private long endTime; //Time at which the gameplay is over
    private long gameLength; //How many seconds game will last
    private long nextCatRequestTime; //Time at which a new cat request will spawn
    private long nextCustomerTime; //Time at which a new customer will spawn
    private CustomerManager customerManager;
    private CatManager catManager;
    private Invoker invoker;
    private ExponentialDistribution catRequestTimeDist;
    private ExponentialDistribution customerSpawnTimeDist;
    private Account account;

    /**
     * @param avgCatRequestRate This is the average time between cat requests spawning
     * @param avgCustomerSpawnRate This is the average time between customers spawning
     * @param invoker This is the invoker that is a part of the in game command pattern
     * @param gameLength This is how long the game should last in seconds
     * **/
    public GameFlow(double avgCatRequestRate, double avgCustomerSpawnRate, long gameLength, Invoker invoker, int nCats){
        this.catManager = new CatManager(nCats);
        this.gameLength = gameLength;
        account = Account.getInstance();
        this.customerManager = CustomerManager.getInstance(Account.getInstance(), CatManager.getInstance());
        this.invoker = invoker;
        catRequestTimeDist = new ExponentialDistribution(avgCatRequestRate);
        customerSpawnTimeDist = new ExponentialDistribution(avgCustomerSpawnRate);
    }

    //This will start a loop that runs until endime is reached
    // This method will tell the invoker to check for new clicks and to execute the next command
    // This method will spawn customers and cat requests at the correct times
    //Every loop will tell the cat/customer managers to check if it's time to decrement patience
    public double startGame(){
        endTime = Instant.now().getEpochSecond() + gameLength;
        while(Instant.now().getEpochSecond() < endTime){
            invoker.doNextCommand();
            customerCheck();
            catCheck();
            customerManager.patienceRoutine();
            catManager.patienceRoutine();
        }
        return account.getAmount();
    }

    /**
     * @param endTime Unix timestamp for when the game should end
     */
    public void setEndTime(long endTime){
        this.endTime = endTime;

    }
    //Helper method which checks if its time for a new customer. If so, tells customer manager to spawn new customer
    private void customerCheck(){
        if(Instant.now().getEpochSecond() >= nextCustomerTime){
            customerManager.spawn();
            calcNextCustomerTime();
        }
    }
    //Helper method which checks if its time for a new cat request. If so, tells cat manager to add a new request(if possible)
    private void catCheck(){
        if(Instant.now().getEpochSecond() >= nextCatRequestTime){
            catManager.spawnRequest();
            calcNextCatTime();
        }
    }
    private void calcNextCustomerTime(){
        nextCustomerTime = Instant.now().getEpochSecond() + Math.round(customerSpawnTimeDist.sample());
    }
    private void calcNextCatTime(){
        nextCatRequestTime = Instant.now().getEpochSecond() + Math.round(catRequestTimeDist.sample());
    }


}
