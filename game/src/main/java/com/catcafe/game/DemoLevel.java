package com.catcafe.game;

public class DemoLevel {
    private Double moneyGoal;
    private Invoker invoker;
    private GameFlow gameFlow;
    private PlayableCharacter playableCharacter;
    private Model model;
    private View view;
    private InGameInteractiveUser controller;

    public DemoLevel(){
        moneyGoal = 5.00;
        invoker = new Invoker();
        gameFlow = new GameFlow(0, 1, 120, invoker, 0);
        model = Model.getInstance();
        playableCharacter = new PlayableCharacter();
        view = new View();
        controller = new InGameInteractiveUser();
        double endMoney = gameFlow.startGame();
        if(endMoney >= moneyGoal){
            System.out.println("You Won!!!");
        }
        else{
            System.out.println("You Lost :(");
        }

    }
}
