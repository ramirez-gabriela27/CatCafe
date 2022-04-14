package com.catcafe.game;
public class InGameCommand extends Command{
    protected PlayableCharacter receiver;
    private Model model;

}

class OrderUpCommand extends InGameCommand{
    public OrderUpCommand(){ description = "Order Up Command"; }
    @Override
    void execute() {
        Model.getInstance().modifyData(receiver.getId(), Attribute.LOCATION, Location.REGISTER);
    }
}

class MakeCoffeeCommand extends InGameCommand{
    public MakeCoffeeCommand(){ description = "Make Coffee Command"; }
    @Override
    void execute() {
        Model.getInstance().modifyData(receiver.getId(), Attribute.LOCATION, Location.COFFEE_MACHINE);
        Coffee coffee = new Coffee();
        System.out.println("coffee made");
        //somehow cause a coffee to pop up on screen
    }
}
class SteamMilkCommand extends InGameCommand{
    @Override
    void execute() {
    }
}
class AddSyrupCommand extends InGameCommand{
    @Override
    void execute() {
    }
}
class ThrowAwayCommand extends InGameCommand{
    @Override
    void execute() {
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