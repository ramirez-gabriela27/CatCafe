package com.catcafe.game;

public interface Patience {
    //lower happiness level
    void decreasePatience();

    //increase happiness level
    void increasePatience();

    //happiness level at 0
    void lostPatience();

    //happiness level at 100
    void fullPatience();

    //return happiness level
    Double getPatienceLevel();

    long getNextDecrementTime();

    //Returns if patience is below some threshold meaning it is at "lowest level"
    Boolean isLostPatience();
}
