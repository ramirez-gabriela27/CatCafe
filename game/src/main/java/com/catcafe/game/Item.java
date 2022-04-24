package com.catcafe.game;
abstract class Item{
    protected String description;
    protected Requestable graphicName;
    //Something here to be connect these with the correct Enum for the image

    /**
     * Compares 2 drinks based off of description
     * TODO: make it so item has only one instance of each string
     * @param item
     * @return
     */
    public boolean compare(Item item){
        String requestedCoffee = this.getDescription();
        String createdCoffee = item.getDescription();
        String lavenderStr = "Lavender";
        String latteStr = "Latte";
        //make sure created Coffee only has one syrup and one milk
        if(getNumInstances(latteStr,createdCoffee) > 1 || getNumInstances(lavenderStr,createdCoffee) > 1){
            return false;
        }
        //check if the descriptions are exactly the same
        else if(createdCoffee.compareTo(requestedCoffee) == 0){
            return true;
        }
        else{
            return false;
        }
    }
    public Requestable getGraphicName(){return graphicName;}
    public String getDescription() {
        return description;
    }
    public Integer getNumInstances(String substring, String bigString){
        //https://stackoverflow.com/questions/767759/occurrences-of-substring-in-a-string
        int index = 0, count = 0, length = substring.length();
        while( (index = bigString.indexOf(substring, index)) != -1 ) {
            index += length;
            count++;
        }
        return count;
    }
}