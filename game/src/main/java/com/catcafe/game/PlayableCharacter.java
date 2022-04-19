package com.catcafe.game;


public class PlayableCharacter {
    private Item carryingItem;
    //private CoffeeShop shop;
    private int id;

    public int getId() {
        return id;
    }

    public PlayableCharacter(Character selectedCharacter){ //can take in a player name ex. "EMMA" as param
        //Adds graphic info to the view
       id = Model.getInstance().addData(selectedCharacter, Location.REGISTER, Drink.NONE, false);
       carryingItem = null;
    }
    //Empties the character's hands
    public void stopCarryingItem(){
        carryingItem =  null;
        Model.getInstance().modifyData(id, Attribute.DRINK, Drink.NONE);

    }
    public void setCarryingItem(Item item){
        carryingItem = item;
        Model.getInstance().modifyData(id, Attribute.DRINK,item.getGraphicName());
        Model.getInstance().modifyData(id, Attribute.REQUEST,false);

    }

    public Item getCarryingItem() {
        return carryingItem;
    }


}
