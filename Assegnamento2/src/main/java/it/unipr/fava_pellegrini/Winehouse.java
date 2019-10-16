package it.unipr.fava_pellegrini;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Winehouse Class
 * The Wine Store (Database). It has the list of all wines available, the list of the persons registered,
 * a list containing every request of a customer and a list which contains every order successfully elaborated.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */

public class Winehouse {
    private LinkedHashMap<Wine, Integer> wines;
    private ArrayList<Person> users;
    private LinkedHashMap<Person, List<Wine>> request;
    private LinkedHashMap<Person, List<Wine>> orders;

    /**
     * Class constructor.
     *
     * Once the Constructor is called it generates the list to manage the wine store.
     *
     */
    Winehouse(){
        this.wines = new LinkedHashMap<Wine, Integer>();
        this.users = new ArrayList<Person>();
        this.request = new LinkedHashMap<Person, List<Wine>>();
        this.orders = new LinkedHashMap<Person, List<Wine>>();
    };

    public LinkedHashMap<Wine, Integer> getWines() {
        return wines;
    }

    public void setWines(LinkedHashMap<Wine, Integer> wines) {
        this.wines = wines;
    }

    public ArrayList<Person> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Person> users) {
        this.users = users;
    }

    public LinkedHashMap<Person, List<Wine>> getRequest() {
        return request;
    }

    public void setRequest(LinkedHashMap<Person, List<Wine>> request) {
        this.request = request;
    }

    public LinkedHashMap<Person, List<Wine>> getOrders() {
        return orders;
    }

    public void setOrders(LinkedHashMap<Person, List<Wine>> orders) {
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
        wines.put(newWine, newWine.getAmount());
    }

    /**
     * Add a wine to the request list.
     *
     * @param buyerClient the client who makes the request
     * @param requestedWine the requested Wine
     */
    public void requestWine(Client buyerClient, Wine requestedWine){
        request.put(buyerClient, buyerClient.getCart());
        buyerClient.getCart().clear();
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
        for (Wine w : getWines().keySet()) {
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
    public boolean checkAvailability(Wine checkWine) {
        for (Wine w : getWines().keySet()) {
            if (w.equals(checkWine) && w.getAmount() != 0)
                return true;
        }
        return false;
    }
}
