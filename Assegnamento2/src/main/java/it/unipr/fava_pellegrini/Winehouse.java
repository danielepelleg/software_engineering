package it.unipr.fava_pellegrini;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Winehouse Class
 * The Wine Store (Database). It has the list of all wines available, the list of the persons registered,
 * a list containing every request of a customer and a list which contains every order successfully elaborated.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */

public class Winehouse {
    private ArrayList<Wine> wines;
    private ArrayList<Person> users;
    private LinkedHashMap<Client, HashMap<Wine, Integer>> request;
    private LinkedHashMap<Client, HashMap<Wine, Integer>> orders;

    /**
     * Class constructor.
     *
     * Once the Constructor is called it generates the list to manage the wine store.
     *
     */
    Winehouse(){
        this.wines = new ArrayList<Wine>();
        this.users = new ArrayList<Person>();
        this.request = new LinkedHashMap<Client, HashMap<Wine, Integer>>();
        this.orders = new LinkedHashMap<Client, HashMap<Wine, Integer>>();
    };

    public ArrayList<Wine> getWines() {
        return wines;
    }

    public void setWines(ArrayList<Wine> wines) {
        this.wines = wines;
    }

    public ArrayList<Person> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Person> users) {
        this.users = users;
    }

    public LinkedHashMap<Client, HashMap<Wine, Integer>> getRequest() {
        return request;
    }

    public void setRequest(LinkedHashMap<Client, HashMap<Wine, Integer>> request) {
        this.request = request;
    }

    public LinkedHashMap<Client, HashMap<Wine, Integer>> getOrders() {
        return orders;
    }

    public void setOrders(LinkedHashMap<Client, HashMap<Wine, Integer>> orders) {
        this.orders = orders;
    }

    /**
     * Register a person to the wine store.
     *
     * @param newPerson the person to register to the database
     */
    public void Registration(Person newPerson){
        getUsers().add(newPerson);
    }

    /**
     * Add a wine to the store.
     *
     * @param newWine the wine to add to the store
     */
    public void addWine(Wine newWine){
        wines.add(newWine);
    }

    /**
     * Add a wine to the request list.
     *
     * @param buyerClient the client who makes the request
     * @param requestedWine the requested Wine
     * @param bottles the amount of bottles to add
     */
    public void requestWine(Client buyerClient, Wine requestedWine, int bottles){
        HashMap requestList = new HashMap();
        requestList.put(requestedWine,bottles);
        request.put(buyerClient, requestList);
    }

    /**
     * Add a wine to the order list.
     *
     * @param buyerClient the client who makes the order
     * @param orderWine the Wine to order
     * @param bottles the amount of bottles to add
     */
    public void orderWine(Client buyerClient, Wine orderWine, int bottles) {
        HashMap orderList = new HashMap();
        orderList.put(orderWine, bottles);
        orders.put(buyerClient, orderList);
    }

    /**
     * Search the wine requested inside the store
     *
     * @param wine_name name of the wine to search
     * @param wine_year year of the wine to search
     *
     * @return the list containing the wines you searched
     */
    public List<Wine> searchWine(String wine_name, int wine_year) {
        ArrayList<Wine> result = new ArrayList<Wine>();
        for (Wine w : getWines()) {
            if (w.getName().equals(wine_name) && w.getYear() == wine_year)
                result.add(w);
        }
        return result;
    }

    /**
     * Check the availability of the wine requested
     *
     * @param checkWine wine to check whether is in the store
     *
     * @return boolean value, true if present - false if not present
     */
    /*public boolean checkAvailability(HashMap<Wine,Integer> checkList) {
        for (Wine w : getWines()) {
            for (Wine w1 : checkList.keySet()){
                if (w.equals(w1) && w.getBottleAmount() >= checkList.get(w1)){
                    return true;
                }
            }
        }
        return false;
    }*/
    public boolean checkAvailability(Wine checkWine, int wineQuantity) {
        for (Wine w : getWines()) {
            if (w.equals(checkWine) && w.getBottleAmount() >= wineQuantity){
                    return true;
                }
            }
        return false;
    }


    public boolean manageRequest(Client checkClient){
        /*
        this.request.forEach((key, value) -> {
            if (key.equals(checkClient)) {
                value.forEach((key2, value2) -> {
                    if (checkAvailability(key2, value2)) {
                    }
                });
            }
        });
        return false;
         */
        HashMap<Client, HashMap<Wine, Integer>> map = this.request;
        for (HashMap.Entry<Client, HashMap<Wine, Integer>> entry : map.entrySet()) {
            Client key = entry.getKey();
            HashMap<Wine, Integer> value = entry.getValue();
            for(HashMap<Wine, Integer> entry2 : entry.getValue());
            // ...
        }
    }
}
