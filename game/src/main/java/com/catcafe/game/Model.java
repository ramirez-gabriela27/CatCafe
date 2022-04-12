package com.catcafe.game;
import java.util.HashMap;


//Make all of these enums public and put in different files??
enum Attribute{
    CHARACTER,
    DRINK,
    LOCATION,
    CARRYING_ITEM_ID
}
enum Character{
    ANJALA,
    EMMA,
    GABY,
    KATY
}
enum Drink{
    COFFEE,
    LATTE,
    SYRUP_COFFEE,
    SYRUP_LATTE
}

enum Location{
    LINE_0,
    LINE_1,
    LINE_2,
    LINE_3,
    REGISTER,
    COFFEE_MACHINE,
    MILK_STEAMER,
    SYRUPS
}

public class Model {
    private HashMap<Integer, HashMap<Attribute,Object>> beverage;
    private HashMap<Integer, HashMap<Attribute,Object>> human ;

    public Model(){
        beverage = new HashMap<Integer, HashMap<Attribute,Object>>();
        human = new HashMap<Integer, HashMap<Attribute,Object>>();
    }

    public void addDataBeverage(int id, Drink drink, Location location){
        beverage.put(id, new HashMap<Attribute,Object>());
        beverage.get(id).put(Attribute.DRINK, drink);
        beverage.get(id).put(Attribute.LOCATION, location);

        //TODO: Alert view that item with id has been created
    }

    public HashMap<Attribute, Object> getAllDataBeverage(int id){
        return beverage.get(id);
    }
    public Object getDataBeverage(int id, Attribute attribute){
        return beverage.get(id).get(attribute);
    }
    public void modifyDataBeverage(int id, Attribute attribute, Object value){
        beverage.get(id).replace(attribute, value);
        //TODO: Alert view that item with id has changed
    }
    public void addDataHuman(int id, Character character, Location location, int carryingItemId){
        human.put(id, new HashMap<Attribute,Object>());
        human.get(id).put(Attribute.CHARACTER, character);
        human.get(id).put(Attribute.LOCATION, location);
        human.get(id).put(Attribute.CARRYING_ITEM_ID, carryingItemId);
        //TODO: Alert view that item with id has been created
    }

    public HashMap<Attribute, Object> getAllDataHuman(int id){
        return human.get(id);
    }
    public Object getDataHuman(int id, Attribute attribute){
        return human.get(id).get(attribute);
    }
    public void modifyDataHuman(int id, Attribute attribute, Object value){
        human.get(id).replace(attribute, value);
        //TODO: Alert view that item with id has changed
    }
}
