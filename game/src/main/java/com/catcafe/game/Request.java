package com.catcafe.game;
abstract class Request{
    private Long requestStartTime;
    private Item requestedItem;
    public Long getRequestStartTime() {
        return requestStartTime;
    }
}