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
        graphicName = Requestable.COFFEE;
        this.description = "Coffee";
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
        if(bev.getGraphicName() == Requestable.SYRUP_COFFEE || bev.getGraphicName() == Requestable.SYRUP_LATTE){
            graphicName = Requestable.SYRUP_LATTE;
        }
        else{
            graphicName = Requestable.LATTE;
        }
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
        if(bev.getGraphicName() == Requestable.SYRUP_LATTE || bev.getGraphicName() == Requestable.LATTE){
            graphicName = Requestable.SYRUP_LATTE;
        }
        else{
            graphicName = Requestable.SYRUP_COFFEE;
        }
        this.beverage = bev;
    }
    public String getDescription(){
        return beverage.getDescription() + " with syrup";
    }
    public Double getCost(){
        return beverage.getCost() + 0.50;
    }
}