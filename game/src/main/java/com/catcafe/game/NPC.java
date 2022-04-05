package com.catcafe.game;

import java.time.Instant;

public abstract class NPC implements Patience{
    private Request request;
    private double patienceLevel; // between 0 and 1
    private long patienceThreshold; //Seconds between patience level decrements
    private long nextDecrementTime; //Unix timestamp of when to decrement patience next
    private int objectID;

    public NPC(long patienceThreshold){
        this.patienceThreshold = patienceThreshold;
        patienceLevel = 1;
        nextDecrementTime = Instant.now().getEpochSecond() + patienceThreshold;

        //Figure out how objetID is going to work
    }

    @Override
    public void decreasePatience() {

    }

    @Override
    public Boolean isLostPatience() {
        return null;
    }

    @Override
    public void fullPatience() {

    }

    @Override
    public Double getPatienceLevel() {
        return 0.0;
    }

    @Override
    public long getNextDecrementTime() {
        return 0;
    }

    protected void setNextDecrementTime(){

    }
    @Override
    public void increasePatience() {

    }

    @Override
    public void lostPatience() {

    }
}

class Cat extends NPC{

    public Cat(){
        super();
        //Cats start off with no request
        request = null;
    }
    /**
     *
     * @param item Item that is being presented to the cat to attempt to fulfill the request (It doesn't have to be a cat
     *             item however, giving a cat a beverage will not ever fulfill their request)
     * @return a boolean of whether or not the request was fulfilled.
     */
    public Boolean attemptFulfillRequest(Item item){
        return false;
    }

}

class Customer extends NPC{
    public Customer(){
        super();
        //On creation create a new random request
    }
}