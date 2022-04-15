package com.catcafe.game;

import java.util.ArrayList;

/**
 * COMMAND Pattern
 */
public class InGameInteractiveUser extends InteractiveUser{
    ArrayList<Command> commandOptions;
    private Invoker invoker;
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

    public Invoker getInvoker() {
        return invoker;
    }
    //TODO: Implement a method that can be called by the view on button click to "press the button" for a command
}