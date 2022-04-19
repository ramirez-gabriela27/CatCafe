package com.catcafe.game;

//https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
public class DemoLevel extends Level{
    public DemoLevel(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        moneyGoal = 5.00;
        gameFlow = new GameFlow(1, 3, 120, invoker, 0);
    }
}
