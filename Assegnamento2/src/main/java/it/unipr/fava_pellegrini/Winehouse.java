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
    private ArrayList<Bottle> bottles;
    private ArrayList<Person> users;
    private ArrayList<Order> request;
    private ArrayList<Order> orders;

    /**
     * Class constructor.
     * <p>
     * Once the Constructor is called it generates the list to manage the wine store.
     */
    Winehouse() {
        this.bottles = new ArrayList<Bottle>();
        this.users = new ArrayList<Person>();
        this.request = new ArrayList<Order>();
        this.orders = new ArrayList<Order>();
    }

    ;

    public ArrayList<Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(ArrayList<Bottle> bottles) {
        this.bottles = bottles;
    }

    public ArrayList<Person> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Person> users) {
        this.users = users;
    }

    public ArrayList<Order> getRequest() {
        return request;
    }

    public void setRequest(ArrayList<Order> request) {
        this.request = request;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * Register a person to the wine store.
     *
     * @param newPerson the person to register to the database
     */
    public void Registration(Person newPerson) {
        getUsers().add(newPerson);
    }

    /**
     * Add a wine to the store.
     *
     * @param newBottle the wine to add to the store
     */
    public void addBottle(Bottle newBottle) {
        bottles.add(newBottle);
    }

    /**
     * Add a wine to the request list.
     *
     * @param buyerClient   the client who makes the request
     * @param requestedWine the requested Wine
     * @param bottles       the amount of bottles to add
     */
    public void requestWine(Client buyerClient, Wine requestedWine, int bottles) {
    }

    /**
     * Add a wine to the order list.
     *
     * @param buyerClient the client who makes the order
     * @param orderWine   the Wine to order
     * @param bottles     the amount of bottles to add
     */
    public void orderWine(Client buyerClient, Wine orderWine, int bottles) {
    }

    /**
     * Search the wine requested inside the store
     *
     * @param wine_name name of the wine to search
     * @param wine_year year of the wine to search
     * @return the list containing the wines you searched
     */
    public List<Bottle> searchWine(String wine_name, int wine_year) {
        ArrayList<Bottle> result = new ArrayList<Bottle>();
        for (Bottle b : getBottles()) {
            if (b.getWine().getName().equals(wine_name) && b.getWine().getYear() == wine_year)
                result.add(b);
        }
        return result;
    }

    /**
     * Check the availability of the wine requested
     *
     * @param checkWine wine to check whether is in the store
     * @return boolean value, true if present - false if not present
     */
    public void checkAvailability(Wine checkWine, int wineQuantity) {
    }


    public void manageRequest(Client checkClient) {
    }
}
