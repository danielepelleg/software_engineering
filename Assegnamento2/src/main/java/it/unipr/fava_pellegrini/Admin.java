package it.unipr.fava_pellegrini;

import java.util.Scanner;

/**
 * Admin Class - Member Subclass
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
     */
    public Admin(String name, String surname, String username, String password) {
        super(name, surname, username, password);
    }

    public void sellWine(Winehouse store, Order sellOrder){
        for (Order o : store.getOrders()){
            if(store.checkAvailability(o.getWineChosen(), o.getBottleAmount())){
                o.setProcessed(true);
            }
            else{
                Scanner input = new Scanner (System.in);
                System.out.println("The following product is currently not available:\n" + o.toString() +"\n");
                System.out.println("Would you like to be notified when back in stock? (y/n)" );
                String decision;
                decision = input.nextLine();
                boolean loop = true;
                while(loop) {
                    switch (decision) {
                        case "y":
                            o.setNotification(true);
                            System.out.println("Your notification request has been processed. You'll be warned when the bottle comes back in stock.\n" + o.toString() + "\n");
                            loop = false;
                            break;
                        case "n":
                            System.out.println("Restock comes every week! Come back soon!");
                            loop = false;
                            break;
                        default: {
                            System.out.println("Not valid Input. Please try again. ");
                            decision = input.nextLine();
                            break;
                        }
                    }
                }
            }
        }
    }

    public void deleteOrder(Winehouse store, Order removeOrder){
        store.getOrders().remove(removeOrder);
    }
}