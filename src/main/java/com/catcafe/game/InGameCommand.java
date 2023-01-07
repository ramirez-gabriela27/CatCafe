package com.catcafe.game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.SplittableRandom;
//Command Pattern
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
        System.out.println(description);
        model.modifyData(receiver.getId(), Attribute.LOCATION, Location.REGISTER);
        PointOfSale point = PointOfSale.getInstance(Account.getInstance(), CustomerManager.getInstance(Account.getInstance()));
        Pair<Double, Double> orderAmountAndTip = point.orderUp(receiver.getCarryingItem());
        Double orderAmount = orderAmountAndTip.getKey();
        Double tip = orderAmountAndTip.getValue();
        if(orderAmount > 0){
            receiver.stopCarryingItem();
        }else{
            System.out.println("Incorrect order, throw away and try again.");
        }
        model.updateMoneyAmount();
        if(orderAmount > 0) {
            model.moneyChange(orderAmount);
        }
        if(tip > 0){
            model.moneyChangeTip(tip);
        }
    }
}

class MakeCoffeeCommand extends InGameCommand{
    public MakeCoffeeCommand(PlayableCharacter receiver){
        this.receiver = receiver;
        description = "Make Coffee Command"; }
    @Override
    void execute() {
        model.modifyData(receiver.getId(), Attribute.LOCATION, Location.COFFEE_MACHINE);
        //System.out.println("coffee made");
        if(receiver.getCarryingItem() == null)
        {
            Coffee coffee = new Coffee();
            receiver.setCarryingItem(coffee);
            System.out.println("Made some delicious coffee!");
        }else{
            System.out.println("Finish current drink before starting a new one.");
        }
//        Image img = new Image("/resources/assets/beverages/coffee2.png");
//        ImageView imageView = new ImageView(img);
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
        System.out.println("milk steamed");
        if(receiver.getCarryingItem() == null){
            System.out.println("You need to click coffee first");
            return;
        }
        if(receiver.getCarryingItem().graphicName == Requestable.COFFEE){
            Beverage coffee = new Coffee();
            Beverage latte = new Milk(coffee);
            receiver.setCarryingItem(latte);
            System.out.println("milk added to coffee, made latte");
            return;
        }
        else if(receiver.getCarryingItem().graphicName == Requestable.SYRUP_COFFEE){
            Beverage coffee = new Coffee();
            Beverage syrupCofee = new Syrup(coffee);
            Beverage lavLatte = new Milk(syrupCofee);
            receiver.setCarryingItem(lavLatte);
            System.out.println("milk added to lavender coffee, made lavender latte");
            return;
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
        if(receiver.getCarryingItem() == null){
            System.out.println("You need to click coffee first");
            return;
        }
        if(receiver.getCarryingItem().graphicName == Requestable.COFFEE){
            Beverage coffee = new Coffee();
            Beverage syrupCoffee = new Syrup(coffee);
            receiver.setCarryingItem(syrupCoffee);
            System.out.println("syrup added to coffee, made lavender coffee");
            return;
        }
        else if(receiver.getCarryingItem().graphicName == Requestable.LATTE){
            Beverage coffee = new Coffee();
            Beverage latte = new Milk(coffee);
            Beverage lavLatte = new Syrup(latte);
            receiver.setCarryingItem(lavLatte);
            System.out.println("syrup added to latte, made lavender latte");
            return;
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
        PointOfSale point = PointOfSale.getInstance(Account.getInstance(), CustomerManager.getInstance(Account.getInstance()));
        double amount = point.subtractThrowAway(receiver.getCarryingItem());
        model.updateMoneyAmount();
        if(amount > 0 ) {
            model.moneyChange(amount * -1);
        }
        receiver.stopCarryingItem();

    }
}
