package it.unipr.fava_pellegrini;

import java.util.*;

/**
 * Winehouse Class
 * The Wine Store (Database). It has the list of all wines available, the list of the persons registered,
 * a list containing every request of a customer and a list which contains every order requested, on notification or elaborated.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */

public class Winehouse {
    private ArrayList<Bottle> bottles;
    private ArrayList<Person> users;
    private ArrayList<Order> orders;

    /**
     * Class constructor.
     *
     * Once the Constructor is called it generates the lists to manage the wine store.
     *
     */
    Winehouse() {
        this.bottles = new ArrayList<Bottle>();
        this.users = new ArrayList<Person>();
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
     *
     */
    public void Registration(Person newPerson) {
        getUsers().add(newPerson);
    }

    /**
     * Add a wine to the store.
     *
     * @param newBottle the wine to add to the store
     *
     */
    public void addBottle(Bottle newBottle) {
        bottles.add(newBottle);
    }

    /**
     * Add a order to the order list
     *
     * @param newOrder Order to add
     *
     */
    public void addOrder(Order newOrder) {
        this.orders.add(newOrder);
    }

    /**
     * Delete a order from the order list.
     *
     * @param deleteOrder Order to delete
     *
     */
    public void deleteOrder(Order deleteOrder) {
        this.orders.remove(deleteOrder);
    }

    /**
     * Update the bottle amount in the store when it's purchased
     *
     * @param editBottle the bottle to edit
     * @param newAmount the amount bought
     */
    public void updateBottle(Bottle editBottle, int newAmount){
        for (Bottle b : this.bottles){
            if (b.getBottleAmount() >= newAmount)
                b.setBottleAmount(b.getBottleAmount()-newAmount);
        }
    }

    /**
     * Search the wine requested inside the store
     *
     * @param wine_name name of the wine to search
     * @param wine_year year of the wine to search
     *
     * @return the list containing the wines you searched
     *
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
     * @param bottleAmount the amount of bottle to check the availability
     *
     * @return boolean value, true if present - false if not present
     */
    public boolean checkAvailability(Wine checkWine, int bottleAmount) {
        for (Bottle b : bottles){
            if (b.getWine() == checkWine && b.getBottleAmount() >= bottleAmount)
                return true;
        }
        return false;
    }

    /**
     * Return a string showing the bottles list
     *
     * @return String the string
     *
     */
    public String printBottles(){
        if(!this.bottles.isEmpty()){
            int index_b = 0;
            String result = "BOTTLES:\n";
            for (Bottle b : this.getBottles()) {
                index_b++;
                result += b.toString() + "\t\t\t\t <--- B";
                switch (index_b) {
                    case 1:
                        result += "X\n\n";
                        break;
                    case 2:
                        result += "Y\n\n";
                        break;
                    case 3:
                        result += "Z\n\n";
                    default:
                        result += "\n\n";
                        break;
                }

            }
            return result;
        }
        else return "There are no wines available in the store. Try later.";
    }

    /**
     *
     */
    @Override
    public String toString(){
        String show = "USERS:\n";
        int index_u = 0;
        int index_a = 0;
        for (Person p : this.users){
            if (p.getClass() == Client.class) {
                index_u++;
                show += p.toString() + "\t <--- U";
                switch (index_u) {
                    case 1:
                        show += "X\n\n";
                        break;
                    case 2:
                        show += "Y\n\n";
                        break;
                    case 3:
                        show += "Z\n\n";
                    default:
                        break;
                }
            }
            if (p.getClass() == Admin.class) {
                index_a++;
                show += p.toString() + "\t <--- A";
                switch (index_a) {
                    case 1:
                        show += "1\n\n";
                        break;
                    case 2:
                        show += "2\n\n";
                        break;
                    case 3:
                        show += "3\n\n";
                    default:
                        show += "\n\n";
                        break;
                }
            }
        }
        show += printBottles();
        return show;
    }
}
