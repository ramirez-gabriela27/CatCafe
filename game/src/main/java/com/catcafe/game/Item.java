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
        String requestedCoffee = this.getDescription();
        String createdCoffee = item.getDescription();
        System.out.println("xcustomer wants " + requestedCoffee);
        System.out.println("xyou have " +createdCoffee);
        String milkStr = " with milk";
        String syrupStr = " with syrup";
        //make sure created Coffee only has one syrup and one milk
        if(getNumInstances(milkStr,createdCoffee) > 1 || getNumInstances(syrupStr,createdCoffee) > 1){
            System.out.println("Multiple milks or syrups");
            return false;
        }
        //check if the descriptions are exactly the same
        else if(item.getDescription() == this.getDescription()){
            System.out.println("exact match (including order)");
            return true;
        }
        //with syrup with milk == with milk with syrup
        else if(requestedCoffee.contains(" with milk") && requestedCoffee.contains(" with syrup") && createdCoffee.contains(" with milk") && createdCoffee.contains(" with syrup")) {
            System.out.println("both has with milk and with syrup");
            return true;
        }
            //if request has only milk
        else if(requestedCoffee.contains(" with milk") && createdCoffee.contains(" with milk")) {
            System.out.println("both has with milk");
            return true;
        }
                //request has only syrup
        else if(requestedCoffee.contains(" with syrup")&& createdCoffee.contains(" with syrup")) {
            System.out.println("both has with s");
            return true;
        }
        else{
            System.out.println("false 1");
            return false;
            }
    }
    public Drink getGraphicName(){return graphicName;}
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