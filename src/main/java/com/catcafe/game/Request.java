package com.catcafe.game;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

/**
 * Possible requests could be an object pool
 */
abstract class Request{
    protected Long requestStartTime;
    protected Item requestedItem;
    public Request(){
        requestStartTime = Instant.now().getEpochSecond();
    }
    protected Long getRequestStartTime() {
        return requestStartTime;
    }
    protected Item getRequestedItem(){
        return requestedItem;
    }
}

class CustomerRequest extends Request{
    private ArrayList<Beverage> possibleRequests= new ArrayList<Beverage>(){{
        add(new Coffee());
        add(new Milk(new Coffee()));
        add(new Syrup(new Coffee()));
        add(new Milk(new Syrup(new Coffee())));
    }};
    public CustomerRequest(){
        super();
        requestedItem = getRandomRequest();
    }
    private Beverage getRandomRequest(){
        return(possibleRequests.get(new Random().nextInt(possibleRequests.size())));
    }
}
