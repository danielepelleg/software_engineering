package it.unipr.fava_pellegrini;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Winehouse Class
 * The Wine Store (Database). It has the list of all wines available, the list of the persons registered,
 * and a list which contains every order a client does.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */

public class Winehouse {
    private ArrayList<Wine> wines;
    private ArrayList<Person> users;
    private LinkedHashMap<Person, List<Wine>> orders;

    /**
     * Class constructor.
     *
     * Once the Constructor is called it generates the list to manage the wine store.
     *
     */
    Winehouse(){
        this.wines = new ArrayList<Wine>();
        this.users = new ArrayList<Person>();
        this.orders = new LinkedHashMap<Person, List<Wine>>();
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
        wines.add(newWine);
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
}
