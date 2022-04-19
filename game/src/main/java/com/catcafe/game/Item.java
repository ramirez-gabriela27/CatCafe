package com.catcafe.game;
abstract class Item{
    protected String description;
    protected Drink graphicName;
    //Something here to be connect these with the correct Enum for the image

    /**
     * Compares 2 drinks based off of description
     * TODO: make it so item has only one instance of each string
     * @param item
     * @return
     */
    public boolean compare(Item item){
        if(item.getDescription() == this.getDescription()){
            return true;
        }
        else if(this.getDescription().contains(" with milk") && this.getDescription().contains(" with syrup")){
            if(item.getDescription().contains(" with milk") && item.getDescription().contains(" with syrup")){
                return true;
            }
            else if(this.getDescription().contains(" with milk")){
                if(item.getDescription().contains(" with milk")) {
                    return true;
                }
                else if(this.getDescription().contains(" with syrup")) {
                    if (item.getDescription().contains(" with syrup")) {
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public Drink getGraphicName(){return graphicName;}
    public String getDescription() {
        return description;
    }
}