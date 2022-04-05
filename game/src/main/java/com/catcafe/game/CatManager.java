package com.catcafe.game;

import java.util.ArrayList;

public class CatManager {
    //Contains all cats
    private ArrayList<Cat> cats;
    //Contains cats with active requests
    private ArrayList<Cat> requestingCats;
    private CatRequestGenerator catRequestGenerator;

    public CatManager(int nCats){
        //make nCats
    }
    /**
     *
     * @param cat Cat object to be added to all cats
     *
     * This function will likely only be used when first instantiating all of the cats
     */
    public void add(Cat cat){

    }

    /**
     *
     * @param cat Cat to be removed
     */
    public void remove(Cat cat){

    }

    /**
     * Randomly selects a cat with no current request and randomly generates a request for them to have.
     * If all cats have requests does nothing.
     */
    public void spawnRequest(){

    }

    /**
     *
     * @param cat Cat which no longer has a request
     */
    public void removeRequest(Cat cat){
        // This method is resposible for removing the request from the cat and removing this cat from the requestingCats arraylist
    }

    /**
     *
     * @return a random cat
     */
    public Cat getRandomCat(){

    }

    /**
     * Goes through cats with current requests and checks if it is time to decrement their patience
     */
    public void patienceRoutine(){

    }

}
