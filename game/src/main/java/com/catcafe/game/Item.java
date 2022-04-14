package com.catcafe.game;
abstract class Item{
    protected String description;
    //Something here to be connect these with the correct Enum for the image
    public boolean compare(Item item){
        return true;//just place holder until we code actual method to avoid error
    }

    public String getDescription() {
        return description;
    }
}