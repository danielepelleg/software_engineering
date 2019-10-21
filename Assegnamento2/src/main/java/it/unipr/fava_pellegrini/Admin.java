package it.unipr.fava_pellegrini;

import java.util.Scanner;

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
     * Add a bottle to the client's cart
     *
     * @param customer the client
     * @param newBottles the bottle to add
     *
     */
    public void addToCart(Client customer, Bottle newBottles){
        customer.getCart().add(newBottles);
    }

    /**
     * If bottles in stock sell to the clients that made request, add it ot their cart, show them the order summary and update the store,
     * if not available ask the client if to be notified when back in stock, is so show the order
     *
     * @param store the Winehouse Store
     *
     */
    public void sellWine(Winehouse store){
        for (Order o : store.getOrders()){
            if(store.checkAvailability(o.getOrderBottle().getWine(), o.getOrderBottle().getBottleAmount())){
                for(Bottle b : store.getBottles()){
                    if(b.getWine().equals(o.getOrderBottle().getWine()))
                        store.updateBottle(b, o.getOrderBottle().getBottleAmount());
                }
                addToCart(o.getBuyer(), o.getOrderBottle());
                o.setProcessed(true);
                System.out.println("Purchase Successful! Order Summary:\n\n" + o.toString());
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
    public void refillBottle(Winehouse store, Bottle newBottles){
        if(!store.getBottles().isEmpty()){
            for(Bottle b: store.getBottles()){
                if(b.equals(newBottles)) {
                    b.setBottleAmount(b.getBottleAmount() + newBottles.getBottleAmount());
                    System.out.println("The following bottle has been refilled:\n" + b.toString());
                }
            }
            store.addBottle(newBottles);
        }
        else{
            store.addBottle(newBottles);
        }
    }

    /**
     * Print the notification message on the console if the bottle comes back in stock.
     * Ask the client if proceed with order and buying it, if so add to the client's cart and show the order summary.
     *
     * @param store the Winehouse Store
     * @throws InterruptedException if the thread is interrupted
     *
     */
    public void sendNotification(Winehouse store) throws InterruptedException {
        for (Order o : store.getOrders()){
            if(o.isNotification() && !o.isProcessed()){
                for (Bottle b: store.getBottles()){
                    if (o.getOrderBottle().getWine() == b.getWine() && b.getBottleAmount() >= o.getOrderBottle().getBottleAmount()){
                        System.out.println("The notification on the availability of the number of bottles requested has been sent to the following client:\n" + o.getBuyer().toString() +
                                "\n" + o.getBuyer().getSurname() + ", Do you want to buy the bottle? (y/n)");
                        Scanner input = new Scanner(System.in);
                        String decision = input.nextLine();
                        boolean loop = true;
                        while(loop) {
                            switch (decision) {
                                case "y":
                                    store.updateBottle(b, o.getOrderBottle().getBottleAmount());
                                    addToCart(o.getBuyer(), o.getOrderBottle());
                                    o.setProcessed(true);
                                    ProgressBar p = new ProgressBar();
                                    p.progress();
                                    System.out.println("Purchase Successful! Order summary:\n" + o.toString());
                                    loop = false;
                                    break;
                                case "n":
                                    deleteOrder(store, o);
                                    System.out.println("Come back soon!");
                                    loop = false;
                                    break;
                                default: {
                                    System.out.println("Not valid Input. Please try again.");
                                    decision = input.nextLine();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}