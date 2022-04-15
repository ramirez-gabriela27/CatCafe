package com.catcafe.game;

import java.util.ArrayList;

/**
 * Possible requests could be an object pool
 */
abstract class Request{
    protected Long requestStartTime;
    protected Item requestedItem;
    public Request(){
    }
    protected Long getRequestStartTime() {
        return requestStartTime;
    }
    protected Item getRequestedItem(){
        return requestedItem;
    }
}

class CustomerRequest extends Request{
    public CustomerRequest(){
        //TEMMORARY will be changed -> param of item (constructor called from customer manager which generated requests)
        requestedItem = new Coffee();
    }
    private ArrayList<KitchenTool> availableKitchenTools;
    private void setRandomRequest(){}
}

/**
 * NOT CURRENTLY BEING USED SO NOT INCLUDED IN CURRENT CLASS DIAGRAM
 */
class CatRequest extends Request{
    public CatRequest(){

    }
    private ArrayList<CatItem> availableCatRequests;
    private void setRandomRequest(){}
}