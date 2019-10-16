package it.unipr.fava_pellegrini;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    List<Wine> cart = new ArrayList<Wine>();

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
        this.cart = new ArrayList<Wine>();
    }

    public List<Wine> getCart(){return this.cart;}

    public void setCart (List<Wine> cart){ this.cart = cart;}

    /**
     * Register the client to the Winehouse Store
     *
     * @param store the Winehouse the client wants to register to
     */
    public void Registration(@NotNull Winehouse store){
        store.Registration(Client.this);
    }

    public void Login(){
        /* TODO implementing Login (bool-string)? */
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
        List<Wine> result = store.searchWine(wine_name, wine_year);
        if (!result.isEmpty()){
            return result.get(0).toString();
        }
        else
            return " There are no wines with these characteristics";
    }

    /**
     * Add the wine, if present, in the client's cart.
     * Once added, drop the wine amount by 1 unit.
     *
     * @param store the Winehouse Store the client wants to make the request
     * @param buyWine the wine to buy
     *
     */
    public void buyWine(Winehouse store, Wine buyWine, int buyQuantity){
        store.requestWine(Client.this, buyWine, buyQuantity);
        /*if(store.checkAvailability(buyWine, buyQuantity)){
            List<Wine> newCart = new ArrayList<Wine>();
            for (Wine w : this.cart){
                newCart.add(w);
            }
            newCart.add(buyWine);
            store.requestWine(Client.this, buyWine, buyQuantity);
            setCart(newCart);
            buyWine.setAmount(buyWine.getAmount()-1);
        }
        else System.out.println("This wine is not available at the moment.");*/
    }
}
