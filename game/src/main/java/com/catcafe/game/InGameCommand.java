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
        //TODO: Compare carrying item to request
        receiver.stopCarryingItem();
    }
}

class MakeCoffeeCommand extends InGameCommand{
    public MakeCoffeeCommand(){ description = "Make Coffee Command"; }
    @Override
    void execute() {
        Model.getInstance().modifyData(receiver.getId(), Attribute.LOCATION, Location.COFFEE_MACHINE);
        receiver.useKitchenTool(Tool.COFFEE_MAKER);
        System.out.println("coffee made");
        //somehow cause a coffee to pop up on screen
    }
}
class SteamMilkCommand extends InGameCommand{
    @Override
    void execute() {
        Model.getInstance().modifyData(receiver.getId(), Attribute.LOCATION, Location.MILK_STEAMER);
        receiver.useKitchenTool(Tool.MILK_STEAMER);
        System.out.println("milk steamed");
    }
}
class AddSyrupCommand extends InGameCommand{
    @Override
    void execute() {
        Model.getInstance().modifyData(receiver.getId(), Attribute.LOCATION, Location.SYRUPS);
        receiver.useKitchenTool(Tool.SYRUP_STATION);
        System.out.println("syrup added");
    }
}
class ThrowAwayCommand extends InGameCommand{
    @Override
    void execute() {
        Model.getInstance().modifyData(receiver.getId(), Attribute.LOCATION, Location.TRASH);
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