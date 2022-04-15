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
    }
    public NPC(){
        this.patienceThreshold = 5;
        patienceLevel = 1;
        setNextDecrementTime();
    }
    public void removeRequest(){
        System.out.println("NPC id " + objectID + " no longer has a request");
        request = null;

        Model.getInstance().modifyData(objectID, Attribute.DRINK, Drink.NONE);
        Model.getInstance().modifyData(objectID, Attribute.REQUEST, false);
    }
    public void addRequest(Request request){
        if(this.request != null){
            throw new RuntimeException("Cannot add new request when NPC already has a request.");
        }
        System.out.println("NPC id " + objectID + " has a request for " + request.toString());
        this.request = request;
        Model.getInstance().modifyData(objectID, Attribute.DRINK, request.getRequestedItem().getGraphicName());
        Model.getInstance().modifyData(objectID, Attribute.REQUEST, true);
    }
    public Request getRequest(){
        return request;
    }
    public abstract void destroy();


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
/**
 * CAT NOT CURRENTLY BEING USED SO NOT INCLUDED IN CURRENT CLASS DIAGRAM
 */
class Cat extends NPC{

    public Cat(long patienceThreshold){
        super(patienceThreshold);
        //Cats start off with no request
        request = null;
    }

    @Override
    public void destroy() {

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
        objectID = Model.getInstance().addData(Character.randomCharacter(),Model.getInstance().getNextCustomerLocation(),Drink.NONE, false);
    }
    public Customer(){
        super();
        System.out.println("A customer has spawned! Id = " + objectID);
        objectID=Model.getInstance().addData(Character.randomCharacter(),Model.getInstance().getNextCustomerLocation(), Drink.NONE, false);
    }
    @Override
    public void destroy() {
        System.out.println("Customer " + objectID + " is now gone.");
        Model.getInstance().removeData(objectID);

    }


}
