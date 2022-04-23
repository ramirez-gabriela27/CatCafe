package com.catcafe.game;


public class PlayableCharacter {
    private Item carryingItem;
    private static PlayableCharacter theChar;
    //private CoffeeShop shop;
    private int id;

    public int getId() {
        return id;
    }

    private PlayableCharacter(Character selectedCharacter){ //can take in a player name ex. "EMMA" as param
        //Adds graphic info to the view
       id = Model.getInstance().addData(selectedCharacter, Location.REGISTER, Requestable.NONE, false, -1);
       carryingItem = null;
    }
    public static PlayableCharacter getInstance(){
        if(theChar == null){
            throw new RuntimeException("You need to select who the character is before you can get her.");
        }
        else{
            return theChar;
        }
    }

    //PlayableCharacter.setCharacter(Character.ANJALA);
    public static void setCharacter(Character characterChoice){
        theChar = new PlayableCharacter(characterChoice);
    }
    //Empties the character's hands
    public void stopCarryingItem(){
        carryingItem =  null;
        Model.getInstance().modifyData(id, Attribute.DRINK, Requestable.NONE);

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
