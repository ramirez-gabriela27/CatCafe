package com.catcafe.game;

//https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
public class DemoLevel implements Runnable {
    private Double moneyGoal;
    private Invoker invoker;
    private GameFlow gameFlow;
    private PlayableCharacter playableCharacter;
    private Model model;
    private View view;
    private InGameInteractiveUser controller;

    public DemoLevel(InGameInteractiveUser user){
        moneyGoal = 5.00;
        invoker = new Invoker();
        gameFlow = new GameFlow(1, 3, 12, user.getInvoker(), 0);
        model = Model.getInstance();
        playableCharacter = new PlayableCharacter();
        view = new View();
        //controller = new InGameInteractiveUser();

    }
    public void run(){
        double endMoney = gameFlow.startGame();
        if(endMoney >= moneyGoal){
            System.out.println("You Won!!!");
        }
        else{
            System.out.println("You Lost :(");
        }

    }
}
