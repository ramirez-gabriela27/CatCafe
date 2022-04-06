package com.catcafe.game;
abstract class Request{
    private Long requestStartTime;
    //private Item requestedItem;
    //^uncomment when Item class made
    public Long getRequestStartTime() {
        return requestStartTime;
    }
}