package com.catcafe.game;
abstract class Request{
    protected Long requestStartTime;
    protected Item requestedItem;
    public Request(){
    }
    protected Long getRequestStartTime() {
        return requestStartTime;
    }
}