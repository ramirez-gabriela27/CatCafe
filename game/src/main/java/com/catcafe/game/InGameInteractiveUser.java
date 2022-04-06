package com.catcafe.game;
/**
 * COMMAND Pattern
 */
public class InGameInteractiveUser extends InteractiveUser{
    Invoker invoker;
    //assuming this will just use the super for commandClicked(), can be overrided if not
}