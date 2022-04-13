package com.catcafe.game;
public class InGameCommand extends Command{
    private PlayableCharacter receiver;
    private Model model;
}

class OrderUpCommand extends InGameCommand{
    @Override
    void execute() {
    }
}

class MakeCoffeeCommand extends InGameCommand{
    @Override
    void execute() {
    }
}
class SteamMilkCommand extends InGameCommand{
    @Override
    void execute() {
    }
}
class AddSyrupCommand extends InGameCommand{
    @Override
    void execute() {
    }
}
class ThrowAwayCommand extends InGameCommand{
    @Override
    void execute() {
    }
}