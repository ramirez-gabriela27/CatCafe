package com.catcafe.game;

public class testcompare {

    public testcompare() {
        compare();
    }

    void compare() {
        Beverage coffee1 = new Coffee();
        Beverage coffee2 = new Coffee();
        Beverage latte1 = new Milk(coffee2);
        Beverage lavcoffee1 = new Syrup(coffee2);
        Beverage lavlat1 = new Syrup(latte1);
        Beverage lavlat2 = new Milk(lavcoffee1);
        assert (coffee1.compare(coffee2) == true);
        assert (lavlat1.compare(lavlat2) == true);
        assert (coffee1.compare(latte1) == false);
    }
}
