package com.catcafe.game;
import java.util.ArrayList;
/**
 * COMMAND Pattern
 */
public class Invoker {
    ArrayList<Command> commandQueue;
    void setCommand(){}
    void doNextCommand(){}
    void doNow(Command command){}
}