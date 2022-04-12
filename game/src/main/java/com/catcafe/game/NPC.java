package com.catcafe.game;

import java.time.Instant;

public abstract class NPC implements Patience{
    protected Request request;
    protected double patienceLevel; // between 0 and 1
    protected double patienceChangeAmount = 0.25;
    protected long patienceThreshold; //Seconds between patience level decrements
    protected long nextDecrementTime; //Unix timestamp of when to decrement patience next
    protected int objectID;


    public NPC(long patienceThreshold){
        this.patienceThreshold = patienceThreshold;
        patienceLevel = 1;
        setNextDecrementTime();

        //Figure out how objetID is going to work
    }
    public NPC(){
        this.patienceThreshold = 5;
        patienceLevel = 1;
        setNextDecrementTime();
    }

    @Override
    public void decreasePatience() {
        patienceLevel -=patienceChangeAmount;
    }

    @Override
    public Boolean isLostPatience() {
        return patienceLevel == 0;
    }

    @Override
    public void fullPatience() {
        patienceLevel = 1;

    }

    @Override
    public Double getPatienceLevel() {
        return patienceLevel;
    }

    @Override
    public long getNextDecrementTime() {
        return nextDecrementTime;
    }

    protected void setNextDecrementTime(){
        nextDecrementTime = Instant.now().getEpochSecond() + patienceThreshold;
    }
    @Override
    public void increasePatience() {
        patienceLevel += patienceChangeAmount;
    }

    @Override
    public void lostPatience() {
        patienceLevel = 0;
    }
}

class Cat extends NPC{

    public Cat(long patienceThreshold){
        super(patienceThreshold);
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
    public Customer(long patienceThreshold){
        super(patienceThreshold);

    }
    public Customer(){
        super();

        //On creation create a new random request
        request = null;

    }


}
