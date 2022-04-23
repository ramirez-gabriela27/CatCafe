package com.catcafe.game;
import java.util.ArrayList;
/**
 * COMMAND Pattern
 */
public class Invoker {
    ArrayList<Command> commandQueue;
    public Invoker(){
        commandQueue = new ArrayList<>();
    }
    void doNextCommand(){
        if(commandQueue.size() >0) {
            Command currentCommand = commandQueue.get(0);
            System.out.println("executing " + currentCommand.getDescription());
            currentCommand.execute();
            commandQueue.remove(0);
        }
    }
    void addCommand(Command command){
        System.out.println("adding "+command.getDescription()+ " to command queue");
        commandQueue.add(command);
    }
    void doNow(Command command){}
}