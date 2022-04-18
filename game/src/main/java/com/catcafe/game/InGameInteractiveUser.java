package com.catcafe.game;

import java.util.ArrayList;

/**
 * COMMAND Pattern
 */
public class InGameInteractiveUser extends InteractiveUser{
    ArrayList<InGameCommand> commandOptions;
    private Invoker invoker;
    //assuming this will just use the super for commandClicked(), can be overrided if not
    public InGameInteractiveUser(PlayableCharacter receiver){
        commandOptions = new ArrayList<>();
        InGameCommand coffeeCommand = new MakeCoffeeCommand(receiver);
        addCommand(coffeeCommand);
        InGameCommand syrupCommand = new AddSyrupCommand(receiver);
        addCommand(syrupCommand);
        InGameCommand steamMilkCommand = new SteamMilkCommand(receiver);
        addCommand(steamMilkCommand);
        InGameCommand orderUpCommand = new OrderUpCommand(receiver);
        addCommand(orderUpCommand);

        invoker = new Invoker();
    }
    public void addCommand(InGameCommand command){
        commandOptions.add(command);
    }

    public Invoker getInvoker() {
        return invoker;
    }
    //TODO: Implement a method that can be called by the view on button click to "press the button" for a command
}