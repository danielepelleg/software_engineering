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
    public Client(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    public void Registration(Winehouse house){
        house.Registration(Client.this);
    }

    public String searchWine(Winehouse house, String n, int y){
        List<Wine> result = house.searchWine(n, y);
        int quantity = 0;
        for(Wine v : result){
            quantity++;
        }
        if (!result.isEmpty()){
            return result.get(0).toString()  + "\nQuantity: " + quantity;
        }
        else
            return "There are no wines with these characteristics";
    }

}
