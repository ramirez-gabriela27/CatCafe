package com.catcafe.game;

public abstract class KitchenTool {
    protected String name;
    protected double useTime;

    public KitchenTool(double useTime){
        this.useTime = useTime;

    }

    public Beverage applyTool(Beverage beverage){
        return null;
    }

    protected void timer(){

    }

}

class CoffeeMaker extends KitchenTool{

    public CoffeeMaker(double useTime) {
        super(useTime);
    }
}

class SyrupStation extends KitchenTool{

    public SyrupStation(double useTime) {
        super(useTime);
    }
}

class MilkSteamer extends KitchenTool{

    public MilkSteamer(double useTime) {
        super(useTime);
    }
}