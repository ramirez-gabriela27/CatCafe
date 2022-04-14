package com.catcafe.game;

public class Beverage extends Item{
    protected Double cost;
    public Double getCost(){
        return cost;
    }
    public String getDescription(){return description;}
}

class Coffee extends Beverage{
    public Coffee(){
        this.description = "Coffee"; //if "possible" stuff done, delete this. from this line
    }
    public Double getCost(){
        return 3.00;
    }
}

class BeverageDecorator extends Beverage{
    public String getDescription(){
        return description;
    }
}
class Milk extends BeverageDecorator{
    Beverage beverage;
    public Milk(Beverage bev){
        this.beverage = bev;
    }
    public String getDescription(){
        return beverage.getDescription() + " with milk";
    }
    public Double getCost(){
        return beverage.getCost() + 0.50;
    }
}
class Syrup extends BeverageDecorator{
    Beverage beverage;
    public Syrup(Beverage bev){
        this.beverage = bev;
    }
    public String getDescription(){
        return beverage.getDescription() + " with syrup";
    }
    public Double getCost(){
        return beverage.getCost() + 0.50;
    }
}