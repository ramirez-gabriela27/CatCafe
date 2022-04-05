package com.catcafe.game;
/*
* Needs to be updated based on how we can make the objects move
* */
public interface Walkable {
    Double getXCoordinate();

    Double getYCoordinate();

    /*take a distance and direction and make an object move
    * */
    void walk(int dist, int direction);

    /* Moves a walkable object to the destination
    *  Parameters: goal x and y coordinates
    *  General idea: plans a route based on something not sure yet and calls walk method to make object move
    * */
    void reachDestination(Double xCoord, Double yCoord);
}
