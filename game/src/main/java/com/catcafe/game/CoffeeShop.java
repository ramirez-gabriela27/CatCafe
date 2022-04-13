package com.catcafe.game;

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
}
