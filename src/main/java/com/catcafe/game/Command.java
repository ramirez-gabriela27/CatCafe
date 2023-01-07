package com.catcafe.game;
/**
 * COMMAND Pattern
 */
abstract class Command{
    void execute(){}
    String description;
    String getDescription(){
        return description;
    }
}