package com.catcafe.game;

import java.util.HashMap;

public class Model {
    private HashMap<Integer, HashMap<Enum, Enum>> customerRequests;
    private HashMap<Integer, HashMap<Enum, Enum>> catRequests;
    private HashMap<Integer, HashMap<Enum, Enum>> humans;
    private HashMap<Integer, HashMap<Enum, Enum>> cats;
    private HashMap<Integer, HashMap<Enum, Enum>> store;
    private ViewManager view;

    public Model(){

    }

    public void addData(Enum type, int objectId){

    }

    /*return type should be: HashMap<Enum, Enum>*/
    public void getData(Enum type, int objectId){

    }

    public void modifyData(Enum type, int objectId, Enum key, Enum value){

    }

    public void setToDefault(Enum type, int objectId){

    }
}
