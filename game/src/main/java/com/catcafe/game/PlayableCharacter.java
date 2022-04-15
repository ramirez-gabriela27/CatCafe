package com.catcafe.game;


public class PlayableCharacter {
    private Item carryingItem;
    private CoffeeShop shop;
    private int id;

    public int getId() {
        return id;
    }

    public PlayableCharacter(){
        //Adds graphic info to the view
       id = Model.getInstance().addData(Character.EMMA, Location.REGISTER, Drink.NONE, false);
       carryingItem = null;
    }
    //Empties the character's hands
    public void stopCarryingItem(){
        carryingItem =  null;
        Model.getInstance().modifyData(id, Attribute.DRINK, Drink.NONE);

    }
    private void setCarryingItem(Item item){
        carryingItem = item;
        Model.getInstance().modifyData(id, Attribute.DRINK,item.getGraphicName());
        Model.getInstance().modifyData(id, Attribute.REQUEST,false);

    }
    public void useKitchenTool(Tool tool){
        Object myTool = shop.getTool(tool);
        if(myTool instanceof KitchenTool){
            carryingItem = ((KitchenTool) myTool).applyTool((Beverage) carryingItem);
            Model.getInstance().modifyData(id, Attribute.DRINK, carryingItem.getGraphicName());
        }
        else {
            throw new RuntimeException("Cannot useKitchenTool on non Kitchen Tool. Tool tried to use:" + tool);
        }
    }

}
