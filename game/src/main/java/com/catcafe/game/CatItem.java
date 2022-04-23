/**
 * NOT CURRENTLY BEING USED SO NOT INCLUDED IN CURRENT CLASS DIAGRAM
 */

package com.catcafe.game;
public abstract class CatItem extends Item{
    @Override
    public boolean compare(Item item){
        if(this.graphicName == item.graphicName){
            return true;
        }
        else{
            return false;
        }
    }
}
class Food extends CatItem{
    public Food(){
        graphicName = Requestable.CAT_FOOD;
    }
}
class Toy extends CatItem{
    public Toy(){
        graphicName = Requestable.CAT_TOY;
    }
}
class Water extends CatItem{
    public Water(){
        graphicName = Requestable.CAT_WATER;
    }
}

