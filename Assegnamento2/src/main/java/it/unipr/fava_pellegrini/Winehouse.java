package it.unipr.fava_pellegrini;

import java.util.*;

/**
 * Winehouse Class
 * The Wine Store (Database). It has the list of all wines available, the list of the persons registered,
 * and a list which contains every order to be elaborated, on notification or completed.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */

public class Winehouse {
    private String name;
    private ArrayList<Bottle> bottles;
    private ArrayList<Person> users;
    private ArrayList<Order> orders;

    /**
     * This constructor generates an empty Winehouse object.
     */
    Winehouse() {
        this.name = "";
        this.bottles = new ArrayList<Bottle>();
        this.users = new ArrayList<Person>();
        this.orders = new ArrayList<Order>();
    }

    /**
     * This constructor generates a Winehouse object from its parameters.
     */
    Winehouse(String name, ArrayList<Bottle> bottles, ArrayList<Person> users, ArrayList<Order> orders) {
        this.name = name;
        this.bottles = new ArrayList<Bottle>(bottles);
        this.users = new ArrayList<Person>(users);
        this.orders = new ArrayList<Order>(orders);
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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
    public void addUser(Person newPerson) {
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
            if(b.equals(editBottle)){
                if (b.getBottleAmount() >= newAmount)
                    b.setBottleAmount(b.getBottleAmount()-newAmount);
        }
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
    public void searchWine(String wine_name, int wine_year) {
        boolean found = false;
        if(!this.bottles.isEmpty()) {
            for (Bottle b : getBottles()) {
                if (b.getWine().getName().equals(wine_name) && b.getWine().getYear() == wine_year) {
                    System.out.print("SEARCH RESULT:\n");
                    System.out.print(b.toString()+"\n");
                    outofstockWarning(b);
                    found = true;
                }
            }
        }
        if(!found)
            System.out.println("There wine searched is not present in the store.");
    }

    /**
     * If the client is logged, let him purchase the amount of wine chosen.
     *
     * @param buyer   the client
     * @param sellWine the wine to buy
     * @param sellAmount the quantity of bottle to buy
     */
    public void sellWine(Client buyer, Wine sellWine, int sellAmount){
        if (buyer.isLogged()) {
            Order newOrder = new Order(buyer, sellWine, sellAmount);
            if (checkAvailability(sellWine, sellAmount)) {
                for (Bottle b : getBottles()) {
                    if (b.getWine().equals(sellWine))
                        updateBottle(b, sellAmount);
                }
                addOrder(newOrder);
                System.out.println("Your order is being processed please wait . . .");
                newOrder.setProcessed(true);
                System.out.println("Purchase Successful! Order Summary:\n\n" + newOrder.toString());
                this.outofstockWarning(newOrder.getOrderBottle());
            }
            else System.out.println("The bottles searched are not currently available. If you want, try to ask for notification!");
        }
        else System.out.println("Please sign in to make a order.");
    }

    /**
     * Print the notification message on the console if the bottle comes back in stock.
     *
     */
    public void sendNotification(){
        for (Order o : getOrders()){
            if(o.isNotification() && !o.isProcessed()){
                if(checkAvailability(o.getOrderBottle().getWine(), o.getOrderBottle().getBottleAmount())){
                    System.out.println("\nThe notification on the availability of the number of bottles requested has been sent to the following client:\n" + o.getBuyer().toString());
                    o.setNotification(false);
                }
            }
        }
        cleanOrders();
    }

    /**
     * Remove the orders already notified.
     */
    public void cleanOrders(){
        ArrayList<Order> newList = new ArrayList<Order>();
        for (Order o: this.orders){
            if(!o.isNotification() && o.isProcessed()){
                newList.add(o);
            }
        }
        setOrders(newList);
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
     * Show an alert if after a purchase a bottle goes out of stock.
     *
     * @param outofstockBottle the bottle to check
     */
    public void outofstockWarning(Bottle outofstockBottle){
        for (Bottle b : this.bottles){
            if (b.getWine() == outofstockBottle.getWine() && b.getBottleAmount() == 0)
                System.out.println("\n!WARNING! The bottles of the following wine: " + b.getWine().getName() + " of the " + b.getWine().getYear() + ", are now out of stock.\n");
        }
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
            StringBuilder result = new StringBuilder("BOTTLES:\n");
            for (Bottle b : this.getBottles()) {
                index_b++;
                result.append(b.toString()).append("\t\t\t\t <--- B");
                switch (index_b) {
                    case 1:
                        result.append("X\n\n");
                        break;
                    case 2:
                        result.append("Y\n\n");
                        break;
                    case 3:
                        result.append("Z\n\n");
                    default:
                        result.append("\n\n");
                        break;
                }

            }
            return result.toString();
        }
        else return "There are no wines available in the store. Try later.";
    }

    /**
     * Show information about the database
     *
     * @return String the information string
     */
    @Override
    public String toString() {
        StringBuilder show = new StringBuilder("USERS:\n");
        int index_u = 0;
        int index_a = 0;
        for (Person p : this.users) {
            if (p.getClass() == Client.class) {
                index_u++;
                show.append(p.toString()).append("\t <--- U");
                switch (index_u) {
                    case 1:
                        show.append("X\n\n");
                        break;
                    case 2:
                        show.append("Y\n\n");
                        break;
                    case 3:
                        show.append("Z\n\n");
                    default:
                        break;
                }
            }
            if (p.getClass() == Admin.class) {
                index_a++;
                show.append(p.toString()).append("\t <--- A");
                switch (index_a) {
                    case 1:
                        show.append("1\n\n");
                        break;
                    case 2:
                        show.append("2\n\n");
                        break;
                    case 3:
                        show.append("3\n\n");
                    default:
                        show.append("\n\n");
                        break;
                }
            }
        }
        show.append(printBottles());
        return show.toString();
    }
}
