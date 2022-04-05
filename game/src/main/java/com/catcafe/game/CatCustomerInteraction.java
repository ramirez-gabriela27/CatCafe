package com.catcafe.game;

public class CatCustomerInteraction {
    private CatManager catManager;
    private Account account;

    public CatCustomerInteraction(CatManager catManager, Account account){
        this.catManager = catManager;
        this.account = account;
    }

    /**
     * Interaction will select a random cat (with or without request) a based on its patience level will
     * either add a tip to the account, do nothing, or subtract money
     */
    public void interaction(){

    }
}
