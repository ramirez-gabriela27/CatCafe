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
        graphicName = Drink.COFFEE;
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
        if(bev.getGraphicName() == Drink.SYRUP_COFFEE || bev.getGraphicName() == Drink.SYRUP_LATTE){
            graphicName = Drink.SYRUP_LATTE;
        }
        else{
            graphicName = Drink.LATTE;
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
        if(bev.getGraphicName() == Drink.SYRUP_LATTE || bev.getGraphicName() == Drink.LATTE){
            graphicName = Drink.SYRUP_LATTE;
        }
        else{
            graphicName = Drink.SYRUP_COFFEE;
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