package com.catcafe.game;

public class Beverage extends Item{
    private Double cost;
    public Double getCost(){
        return cost;
    }
}

class Coffee extends Beverage{
}

class BeverageDecorator extends Beverage{
    String description;
    public String getDescription(){
        return description;
    }
}
class Milk extends BeverageDecorator{
    Beverage beverage;
}
class Syrup extends BeverageDecorator{
    Beverage beverage;
}