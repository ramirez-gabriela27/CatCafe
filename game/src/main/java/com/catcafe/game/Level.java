package com.catcafe.game;

public abstract class Level implements Runnable{
    //https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
        protected Double moneyGoal;
        protected Invoker invoker;
        protected GameFlow gameFlow;
        protected PlayableCharacter playableCharacter;
        protected Model model;
        protected GamePlay_Controller gameView;
        protected InGameInteractiveUser user;
        public Level(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
            invoker = user.getInvoker();
            model = Model.getInstance();
            this.playableCharacter = playableCharacter;
            this.gameView = gameView;
            this.user = user;
            model.setView(gameView);
    }
    public void run(){
        gameFlow.startGame();
        double endMoney = gameFlow.getEndMoney();
        if(endMoney >= moneyGoal){
            System.out.println("You Won!!!");
            gameView.endGame(true);
        }
        else{
            System.out.println("You Lost :(");
            gameView.endGame(false);
        }
    }
}

//https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-jav

class DemoLevel extends Level{
    public DemoLevel(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        moneyGoal = 5.00;
        gameFlow = new GameFlow(1, 8, 300, invoker, 0);
    }
}
class Level1 extends Level{
    public Level1(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        moneyGoal = 15.00;
        gameFlow = new GameFlow(1, 12, 60, invoker, 0);
    }
}
class Level2 extends Level{
    public Level2(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        moneyGoal = 30.00;
        gameFlow = new GameFlow(1, 10, 90, invoker, 0);
    }
}
class Level3 extends Level{
    public Level3(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        moneyGoal = 40.00;
        gameFlow = new GameFlow(1, 5, 120, invoker, 0);
    }
}


