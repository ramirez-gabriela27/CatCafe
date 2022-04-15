package com.catcafe.game;

import static com.catcafe.game.Tool.*;

enum Tool{
    COFFEE_MAKER,
    MILK_STEAMER,
    SYRUP_STATION,
    POINT_OF_SALE
}
public class CoffeeShop {
    private KitchenTool coffeeMaker;
    private KitchenTool milkSteamer;
    private KitchenTool syrupStation;
    private PointOfSale pointOfSale;
    public CoffeeShop(){
        coffeeMaker = new CoffeeMaker(0);
        milkSteamer = new MilkSteamer(0);
        syrupStation = new SyrupStation(0);
        pointOfSale = PointOfSale.getInstance(Account.getInstance(), CustomerManager.getInstance(Account.getInstance(), CatManager.getInstance()));
    }
    public Object getTool(Tool type){
        switch (type){
            case COFFEE_MAKER:
                return coffeeMaker;
            case MILK_STEAMER:
                return milkSteamer;
            case SYRUP_STATION:
                return syrupStation;
            case POINT_OF_SALE:
                return pointOfSale;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
