package it.unipr.fava_pellegrini;

/**
 * Admin Class - Person Subclass
 * Each admin has the name attribute, the surname, the username,
 * the password and the hashed password generated from the last one
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Admin extends Person {

    /**
     * Class constructor.
     *
     * @param name     the name of the admin to be created
     * @param surname  the surname of the admin to be created
     * @param username the username of the admin to be created
     * @param password the password of the admin to be created
     *
     */
    public Admin(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    /**
     * Delete a order from Order list in Winehouse Class
     *
     * @param store the Winehouse Store
     * @param removeOrder the order to remove
     *
     */
    public void deleteOrder(Winehouse store, Order removeOrder){
        store.deleteOrder(removeOrder);
    }

    /**
     * Refill a bottle if already present in the store, add a new one to the list if not present
     *
     * @param store the Winehouse Store
     * @param newBottles the bottle to refill or add
     *
     */
    public void refillBottle(Winehouse store, Bottle newBottles, int newAmount){
        boolean present = false;
        if(!store.getBottles().isEmpty()){
            for(Bottle b: store.getBottles()){
                if(b.getWine().getName() == newBottles.getWine().getName() && b.getWine().getYear() == newBottles.getWine().getYear()) {
                    present = true;
                    b.setBottleAmount(b.getBottleAmount() + newAmount);
                    System.out.println("The following bottle has been refilled:\n" + b.toString());
                    store.sendNotification();
                }
            }
            if(!present)
                store.addBottle(newBottles);
        }
        else{
            store.addBottle(newBottles);
        }
    }
}