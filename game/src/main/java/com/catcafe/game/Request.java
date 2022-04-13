package com.catcafe.game;

import java.util.ArrayList;

abstract class Request{
    protected Long requestStartTime;
    protected Item requestedItem;
    public Request(){
    }
    protected Long getRequestStartTime() {
        return requestStartTime;
    }
}

class CustomerRequest extends Request{
    private ArrayList<KitchenTool> availableKitchenTools;
    private void setRandomRequest(){}
}


class CatRequest extends Request{
    private ArrayList<CatItem> availableCatRequests;
    private void setRandomRequest(){}
}