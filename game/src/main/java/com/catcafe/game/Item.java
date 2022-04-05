package com.catcafe.game;
abstract class Item{
    private String description;
    public boolean compare(Item item){
        return true;//just place holder until we code actual method to avoid error
    }

    public String getDescription() {
        return description;
    }
}