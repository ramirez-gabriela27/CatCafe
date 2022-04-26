package com.catcafe.game;
import java.io.IOException;
import java.util.*;


//Make all of these enums public and put in different files??


enum Requestable{
    COFFEE,
    LATTE,
    SYRUP_COFFEE,
    SYRUP_LATTE,
    CAT_FOOD,
    CAT_TOY,
    CAT_WATER,
    NONE
}
enum Location{
    LINE_0,
    LINE_1,
    LINE_2,
    LINE_3,
    OFF_SCREEN,
    REGISTER,
    COFFEE_MACHINE,
    MILK_STEAMER,
    SYRUPS,
    TRASH,
    CAT_1,
    CAT_2,
    CAT_FOOD_BAG,
    CAT_WATER_BOWL,
    CAT_TOY_BIN,
    WAITING_1,
    WAITING_2,
    WAITING_3,
    WAITING_4;

}
enum Character{
    ANJALA,
    EMMA,
    GABY,
    KATY,
    LEO,
    SPOT;

    //https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
    private static final List<Character> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random RANDOM = new Random();
    public static Character randomCharacter()  {
        return VALUES.get(RANDOM.nextInt(4));
    }
}
enum Attribute{
    CHARACTER,
    DRINK,
    LOCATION,
    REQUEST,
    PATIENCE
}



public class Model {
    private HashMap<Integer, HashMap<Attribute,Object>> human;
    private HashMap<Integer, HashMap<Attribute,Object>> cat;
    private String moneyAmount;
    private int nextId;
    private GamePlay_Controller view;
    private final Location[] lineLocations = {Location.LINE_0, Location.LINE_1, Location.LINE_2, Location.LINE_3};
    private static Model theModel = new Model();
    //https://www.baeldung.com/java-initialize-hashmap
    private HashMap<Location, Integer> occupiedLocations;
    private Model(){
        nextId = 0;
        human = new HashMap<Integer, HashMap<Attribute,Object>>();
        occupiedLocations = new HashMap<Location, Integer>()
        {{
            put(Location.LINE_0, -1);
            put(Location.LINE_1, -1);
            put(Location.LINE_2, -1);
            put(Location.LINE_3, -1);
        }};
        moneyAmount = Account.getInstance().getAmountString();
    }
    public static synchronized Model getInstance(){
        if(theModel == null){
            theModel = new Model();
        }
        return theModel;
    }

    private synchronized int getNextId(){
        int thisId = nextId;
        nextId +=1;
        return thisId;
    }
    public synchronized Location getNextCustomerLocation(){

        Location location;
        for(int i =lineLocations.length-2; i>=0; i--){
            location = lineLocations[i];
            if(occupiedLocations.get(location) != -1){
                return lineLocations[i+1];
            }
         }
        if(occupiedLocations.get(lineLocations[0])==-1){
            return lineLocations[0];
        }
        else {
            throw new RuntimeException("All customer locations are occupied.");
        }
    }
    private synchronized Location getFirstAvailableLineSpot(){
        for(Location location: lineLocations ){
             if(occupiedLocations.get(location) == -1){
                return location;
             }
         }
        throw new RuntimeException("All customer locations are occupied.");
    }

    private synchronized void updateLocationStatus(Location location, Integer id){
        occupiedLocations.replace(location, id);
    }
    //Returns the Id
   /* public int addDataBeverage( Drink drink, Location location){
        int id = getNextId();
        beverage.put(id, new HashMap<Attribute,Object>());
        beverage.get(id).put(Attribute.DRINK, drink);
        beverage.get(id).put(Attribute.LOCATION, location);
        //TODO: Alert view that item with id has been created
        return id;

    }

    public HashMap<Attribute, Object> getAllDataBeverage(int id){
        return beverage.get(id);
    }
    public Object getDataBeverage(int id, Attribute attribute){
        return beverage.get(id).get(attribute);
    }
    public void modifyDataBeverage(int id, Attribute attribute, Object value){
        beverage.get(id).replace(attribute, value);
        //TODO: Alert view that item with id has changed
    }
*/
    //carryingItemId is -1 when theyre not carrying anything

    /**
     *
     * @param character - A name from the Charater Enum to say which image should be used
     * @param location - A location from the location enum which says where they are. If this is a customer
     *                 then this argument should be Model.getInstance().getNextCustomerLocation()
     * @param drink - If there is a drink associated with this character (either carrying or a request) specify
     *              the type by picking from the Drink enum. If theres no drink associated put Drink.NONE
     * @param hasRequest - If this is a request (from a customer) put true. If they are carrying this item then false
     * @param patienceLevel - Level of patience that character has. -1 if doesnt have patience (ex. playable character)
     * @return returns the id which will be used to modify this data.
     */
    public synchronized int addData(Character character, Location location, Requestable drink, Boolean hasRequest, double patienceLevel){
        int id = getNextId();
        if(human.containsKey(id)){
            printModel();
            throw new RuntimeException("Something bad happened with model ids. multiple have id: " + id);
        }
        human.put(id, new HashMap<Attribute,Object>());
        human.get(id).put(Attribute.CHARACTER, character);
        human.get(id).put(Attribute.LOCATION, location);
        human.get(id).put(Attribute.DRINK, drink);
        human.get(id).put(Attribute.REQUEST, hasRequest);
        human.get(id).put(Attribute.PATIENCE, patienceLevel);
        updateLocationStatus(location, id);
        if(hasRequest){
            try {
                System.out.println("HEREEEE");
                view.addNPC(id, character, location);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //TODO: Alert view that item with id has been created
        // view.alertChange(id)
        lineMoveUp();
        printModel();
        return id;
    }

    public HashMap<Attribute, Object> getAllData(int id){
        return human.get(id);
    }
    public Object getData(int id, Attribute attribute){
        return human.get(id).get(attribute);
    }
    public synchronized void modifyData(int id, Attribute attribute, Object value){
        if(attribute == Attribute.LOCATION){
            //make previous location false
            updateLocationStatus((Location) getData(id, Attribute.LOCATION), -1);
            // make new location true
            updateLocationStatus((Location) value, id);
            //System.out.println("ID = " + id);
            if((Double)getData(id, Attribute.PATIENCE)==-1){
                view.updateLocation(id, (Location) value);
            }
            else{
                view.updateLocationNoWalk(id, (Location) value);
            }

        }
        human.get(id).replace(attribute, value);
        //TODO: Alert view that item with id has changed
    }
    public synchronized void removeData(int id){
        //make it so nobody is standing there
        updateLocationStatus((Location) getData(id, Attribute.LOCATION), -1);
        human.remove(id);
        view.removeNPC(id);
        lineMoveUp();
    }

    /**
     * Deletes all data
     */
    public synchronized void clearModel(){
        //System.out.println("Game end");
        //printModel();
        /**for(int key: human.keySet()){
            removeData(key);
        }
         **/
        human = new HashMap<Integer, HashMap<Attribute,Object>>();
        occupiedLocations = new HashMap<Location, Integer>()
        {{
            put(Location.LINE_0, -1);
            put(Location.LINE_1, -1);
            put(Location.LINE_2, -1);
            put(Location.LINE_3, -1);
        }};
    }
    private synchronized void lineMoveUp(){
        System.out.println("line move up");
        //printModel();
        if(occupiedLocations.get(Location.LINE_0)==-1){
            //move everyone up
            for(Location spot: lineLocations){
                System.out.println(spot);
                if(occupiedLocations.get(spot)!=-1){
                    //System.out.println("here3");
                    //found a person lets move them to the first empty spot
                    int id = occupiedLocations.get(spot);
                   //System.out.println("here4");
                    modifyData(id, Attribute.LOCATION, getFirstAvailableLineSpot());
                    //printModel();
                }
            }
        }
        printModel();
    }
    public void setView(GamePlay_Controller view){
        this.view = view;
    }
    private void printModel(){
        for(int key: human.keySet()){
            System.out.println(key + " " + " " + human.get(key));
        }
    }
    public void updateMoneyAmount(){
        moneyAmount = Account.getInstance().getAmountString();
        view.updateMoneyDisplay(moneyAmount);
    }

}
