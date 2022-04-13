package com.catcafe.game;
public class MenuCommand extends Command{
    private Driver receiver;
    //^^commented out because Driver not made yet
}
class StartGameCommand extends MenuCommand{
    @Override
    void execute() {
    }
}
class GoToStoreCommand extends MenuCommand{
    @Override
    void execute() {
    }
}
class StorePurchaseCommand extends MenuCommand{
    @Override
    void execute() {
    }
}

class ViewLevelsCommand extends MenuCommand{
    @Override
    void execute() {
    }
}
class EndGameCommand extends MenuCommand{
    @Override
    void execute() {
    }
}