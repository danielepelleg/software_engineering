package it.unipr.fava_pellegrini;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Client Class - Person Subclass
 * Each Client has the name attribute, the surname, the username,
 * the password ,the hashed password generated from the last one and
 * a cart in which puts the wines he requests.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Client extends Person {
    List<Bottle> cart = new ArrayList<Bottle>();

    /**
     * Class constructor.
     * When called, the constructor generates an empty cart for every customer.
     *
     * @param name the name of the member to be created
     * @param surname the surname of the member to be created
     * @param username the username of the member to be created
     * @param password the password of the member to be created
     *
     */
    Client(String name, String surname, String username, String password){
        super(name, surname, username, password);
        this.cart = new ArrayList<Bottle>();
    }

    public List<Bottle> getCart(){return this.cart;}

    public void setCart (List<Bottle> cart){ this.cart = cart;}

    /**
     * Register the client to the Winehouse Store
     *
     * @param store the Winehouse the client wants to register to
     */
    public void Registration(@NotNull Winehouse store){
        store.Registration(Client.this);
    }

    /**
     * Sign in the client to the Winehouse Store
     *
     * @param store the Winehouse the client wants to sign in to
     * @param checkUsername the username credential chosen yo sign in
     * @param checkPassword the password credential chosen to sign in
     */
    public void Login(Winehouse store, String checkUsername, String checkPassword){
        /* TODO implementing Login (bool-string)? */
        Client tempClient = new Client("Client", "Testing", checkUsername, checkPassword);
        tempClient.setPassword(checkPassword);
        for (Person p : store.getUsers()){
            if (p.getUsername().equals(checkUsername) && p.getPassword().equals(tempClient.getPassword())){
                System.out.println("Login Successful!");
            }
            else System.out.println("Bad Login. The username or password you have entered is invalid.");
        }
    }

    /**
     * Search the wine the client choose in the store
     *
     * @param store the Winehouse store the client wants to search into
     * @param wine_name the name of the wine searched
     * @param wine_year the year of the wine searched
     *
     * @return a String with the information about the wine searched. If the wine
     *          is not present in the store informs the client about it
     */
    public String searchWine(Winehouse store, String wine_name, int wine_year){
        List<Bottle> result = store.searchWine(wine_name, wine_year);
        if (!result.isEmpty()){
            return result.get(0).toString();
        }
        else
            return " There are no wines with these characteristics";
    }

    /**
     * Add the wine, if present, in the order list.
     * If the wine is not present, ask the client if to be notified when available.
     * If the client accepts, create a new Request in Winehouse
     *
     * @param store the Winehouse Store the client wants to make the request
     * @param buyWine the wine to buy
     * @param bottles the quantity of bottle to buy
     *
     */
    public void buyWine(Winehouse store, Wine buyWine, int bottles) throws IOException, InterruptedException {
        /*
        if(store.checkAvailability(buyWine, bottles)) {
            Order newOrder = new Order(Client.this, buyWine, bottles);
            store.addOrder(newOrder);
            System.out.println("Your order is being processed please wait");
            String anim= "|/-\\";
            for (int x =0 ; x <= 100 ; x++) {
                String data = "\r" + anim.charAt(x % anim.length()) + " " + x;
                System.out.write(data.getBytes());
                Thread.sleep(30);
            }
            System.out.println("\tDone! \nPurchase Successful!\n");
            System.out.println(newOrder.toString());
        }
        else{
            Scanner input = new Scanner (System.in);
            System.out.println("The product is currently not available. ");
            System.out.println("Would you like to be notified when back in stock? (y/n)" );
            String decision;
            decision = input.nextLine();
            boolean loop = true;
            while(loop) {
                switch (decision) {
                    case "y":
                        Order newRequest = new Order(Client.this, buyWine, bottles);
                        store.addRequest(newRequest);
                        System.out.println("Your notification request has been processed. You'll be warned when the bottle comes back in stock. ");
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
        }*/
        Order newOrder = new Order(Client.this, buyWine, bottles);
        store.addOrder(newOrder);
        System.out.println("Your order is being processed please wait");
        String anim= "|/-\\";
        for (int x =0 ; x <= 100 ; x++) {
            String data = "\r" + anim.charAt(x % anim.length()) + " " + x;
            System.out.write(data.getBytes());
            Thread.sleep(30);
        }
        System.out.println("\tDone! \nYour order has been added successfully!\n");
        System.out.println(newOrder.toString());
    }

    public void addToCart(Bottle newBottles){
        this.cart.add(newBottles);
    }

    public String showCart(){
        String result = "User cart:\n";
        for (Bottle b : getCart()){
            result = result + "Wine:\n" + b.getWine() + " Number of bottles: " + b.getBottleAmount() + "\n";
        }
        return result;
    }
}
