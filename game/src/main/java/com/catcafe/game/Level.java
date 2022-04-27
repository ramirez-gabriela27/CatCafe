package com.catcafe.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Level implements Runnable{
    //https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
        protected Double moneyGoal;
        protected Invoker invoker;
        protected GameFlow gameFlow;
        protected PlayableCharacter playableCharacter;
        protected Model model;
        protected GamePlay_Controller gameView;
        protected InGameInteractiveUser user;
        protected Double highScore;
        protected String path;
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
        updateHighScore(endMoney);
    }
    public double getHighScore(){return highScore;}
    protected double fetchHighScore(String path){
            double score;
            //https://www.w3schools.com/java/java_files_read.asp
            try {
                File myObj = new File(path);
                Scanner myReader = new Scanner(myObj);
                if (myReader.hasNextLine()) {
                    return(myReader.nextDouble());
                }
                else {
                    score = 0.0;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                score = 0.0;
            }
            return score;
    }
    protected void updateHighScore(double newScore){
            if(newScore > highScore){
                highScore = newScore;
            }
        //https://www.w3schools.com/java/java_files_create.asp
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred. (Create File)");
            e.printStackTrace();
        }

        try {
            //https://whaa.dev/how-to-overwrite-a-file-in-java
            FileWriter myWriter = new FileWriter(path, false);
            myWriter.write(String.valueOf(highScore));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

//https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-jav

class DemoLevel extends Level{
    public DemoLevel(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        path = "src/main/resources/assets/HighScores/DemoLevel.txt";
        highScore = fetchHighScore(path);
        moneyGoal = 18.00;
        gameFlow = new GameFlow(1, 8, 60, invoker, 0);
    }
}
//game lengths - 60,90,120
class Level1 extends Level{
    public Level1(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        path = "src/main/resources/assets/HighScores/Level1.txt";
        highScore = fetchHighScore(path);
        moneyGoal = 15.00;
        gameFlow = new GameFlow(1, 12, 60, invoker, 0);
        System.out.println("LEVEL 1");
    }
}
class Level2 extends Level{
    public Level2(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        path = "src/main/resources/assets/HighScores/Level2.txt";
        highScore = fetchHighScore(path);
        moneyGoal = 30.00;
        gameFlow = new GameFlow(1, 10, 90, invoker, 0);
        System.out.println("LEVEL 2");
    }
}
class Level3 extends Level{
    public Level3(InGameInteractiveUser user, PlayableCharacter playableCharacter, GamePlay_Controller gameView){
        super(user, playableCharacter, gameView);
        path = "src/main/resources/assets/HighScores/Level3.txt";
        highScore = fetchHighScore(path);
        moneyGoal = 40.00;
        gameFlow = new GameFlow(1, 5, 120, invoker, 0);
        System.out.println("LEVEL 3");
    }
}



