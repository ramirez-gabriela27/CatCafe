package com.catcafe.game;

import java.util.ArrayList;

/**
 * COMMAND Pattern
 */
public class InGameInteractiveUser extends InteractiveUser{
    ArrayList<Command> commandOptions;
    Invoker invoker;
    //assuming this will just use the super for commandClicked(), can be overrided if not
    public InGameInteractiveUser(){
        commandOptions = new ArrayList<>();
        Command coffeeCommand = new MakeCoffeeCommand();
        addCommand(coffeeCommand);
        invoker = new Invoker();
    }
    public void addCommand(Command command){
        commandOptions.add(command);
    }
}