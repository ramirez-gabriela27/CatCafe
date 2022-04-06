package com.catcafe.game;

public abstract class KitchenTool {
    protected String name;
    protected double useTime;

    public KitchenTool(double useTime){
        this.useTime = useTime;

    }

    public abstract Beverage applyTool(Beverage beverage);

    protected void timer(){
        //Maybe no timer for now?
    }

}

class CoffeeMaker extends KitchenTool{

    public CoffeeMaker(double useTime) {
        super(useTime);
    }

    @Override
    public Beverage applyTool(Beverage beverage) {
        //return new Coffee(beverage);
        return null;
    }
}

class SyrupStation extends KitchenTool{

    public SyrupStation(double useTime) {
        super(useTime);
    }

    @Override
    public Beverage applyTool(Beverage beverage) {
        //return new Syrup(beverage);
        return null;
    }
}

class MilkSteamer extends KitchenTool{

    public MilkSteamer(double useTime) {
        super(useTime);
    }

    @Override
    public Beverage applyTool(Beverage beverage) {
        //return new Milk(beverage);
        return null;
    }
}