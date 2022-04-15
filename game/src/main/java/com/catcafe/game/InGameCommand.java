package com.catcafe.game;

import java.util.SplittableRandom;

public class InGameCommand extends Command{
    protected PlayableCharacter receiver;
    protected Model model = Model.getInstance();
}

class OrderUpCommand extends InGameCommand{
    public OrderUpCommand(PlayableCharacter receiver){
        this.receiver = receiver;
        description = "Order Up Command"; }
    @Override
    void execute() {
        model.modifyData(receiver.getId(), Attribute.LOCATION, Location.REGISTER);
        //receiver.useKitchenTool(Tool.POINT_OF_SALE);
        System.out.println(description);
    }
}

class MakeCoffeeCommand extends InGameCommand{
    public MakeCoffeeCommand(PlayableCharacter receiver){
        this.receiver = receiver;
        description = "Make Coffee Command"; }
    @Override
    void execute() {
        model.modifyData(receiver.getId(), Attribute.LOCATION, Location.COFFEE_MACHINE);
        //receiver.useKitchenTool(Tool.COFFEE_MAKER);g
        //System.out.println("coffee made");
        Coffee coffee = new Coffee();
        receiver.setCarryingItem(coffee);
        //somehow cause a coffee to pop up on screen
    }
}
class SteamMilkCommand extends InGameCommand{
    public SteamMilkCommand(PlayableCharacter receiver){
        this.receiver = receiver;
        description = "Steam Milk Command"; }
    @Override
    void execute() {
        model.modifyData(receiver.getId(), Attribute.LOCATION, Location.MILK_STEAMER);
        //receiver.useKitchenTool(Tool.MILK_STEAMER);
        System.out.println("milk steamed");
        if(receiver.getCarryingItem().graphicName == Drink.COFFEE){
            Beverage coffee = new Coffee();
            Beverage latte = new Milk(coffee);
            receiver.setCarryingItem(latte);
            System.out.println("milk added to coffee, made latte");
        }
        else if(receiver.getCarryingItem().graphicName == Drink.SYRUP_COFFEE){
            Beverage coffee = new Coffee();
            Beverage syrupCofee = new Syrup(coffee);
            Beverage lavLatte = new Milk(syrupCofee);
            receiver.setCarryingItem(lavLatte);
            System.out.println("milk added to lavender coffee, made lavender latte");
        }
    }
}
class AddSyrupCommand extends InGameCommand{
    public AddSyrupCommand(PlayableCharacter receiver){
        this.receiver = receiver;
        description = "Add Syrup Command";}
    @Override
    void execute() {
        model.modifyData(receiver.getId(), Attribute.LOCATION, Location.SYRUPS);
        //receiver.useKitchenTool(Tool.SYRUP_STATION);
        if(receiver.getCarryingItem().graphicName == Drink.COFFEE){
            Beverage coffee = new Coffee();
            Beverage syrupCoffee = new Syrup(coffee);
            receiver.setCarryingItem(syrupCoffee);
            System.out.println("syrup added to coffee, made lavender coffee");
        }
        else if(receiver.getCarryingItem().graphicName == Drink.LATTE){
            Beverage coffee = new Coffee();
            Beverage latte = new Milk(coffee);
            Beverage lavLatte = new Syrup(latte);
            receiver.setCarryingItem(lavLatte);
            System.out.println("syrup added to latte, made lavender latte");
        }

    }
}
class ThrowAwayCommand extends InGameCommand{
    public ThrowAwayCommand(PlayableCharacter receiver){
        this.receiver = receiver;
        description = "Throw Away Command";}
    @Override
    void execute() {
        model.modifyData(receiver.getId(), Attribute.LOCATION, Location.TRASH);
        receiver.stopCarryingItem();
    }
}

//TODO: Cat commands for next sprint
class ServeCatCommand extends InGameCommand{
    @Override
    void execute() {
    }
}
class GetCatWaterCommand extends InGameCommand{
    @Override
    void execute() {
    }
}
class GetCatFoodCommand extends InGameCommand{
    @Override
    void execute() {
    }
}