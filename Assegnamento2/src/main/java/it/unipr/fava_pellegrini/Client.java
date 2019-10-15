package it.unipr.fava_pellegrini;

import org.jetbrains.annotations.NotNull;
import java.util.List;

/**
 * Client Class - Person Subclass
 * Each Client has the name attribute, the surname, the username,
 * the password and the hashed password generated from the last one
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */

public class Client extends Person {

    /**
     * Class constructor.
     *
     * @param name the name of the member to be created
     * @param surname the surname of the member to be created
     * @param username the username of the member to be created
     * @param password the password of the member to be created
     *
     */
    Client(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    /**
     * Register the client to the Winehouse Store
     *
     * @param store the Winehouse the client wants to register to
     */
    public void Registration(@NotNull Winehouse store){
        store.Registration(Client.this);
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

}
