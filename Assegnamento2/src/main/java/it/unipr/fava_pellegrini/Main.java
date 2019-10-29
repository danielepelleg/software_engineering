package it.unipr.fava_pellegrini;

import java.io.IOException;

/**
 * Main Class
 * Test, through a simulation, the classes and methods created
 *
 * 1) The Winehouse is initialized with some users, an admin and some wines
 * 2a) User UX signs in and buy some of the bottle BX
 * 2b) User UY signs in and buy all the bottle BY
 * 3) User UZ signs to buy some of the bottle UY now gone out of stock, so ask to be notified when
 *      the bottle BY comes back available
 * 4) The employee refill the missing bottles UY and the system send a notification to the user UZ
 *      about the new availability of the bottle
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("------ 1) The Winehouse is initialized with some users, an admin and some wines ------");
        Winehouse store = new Winehouse();
        Client UX = new Client("Giacomo", "Pini", "Jack", "winelover");
        Client UY = new Client("Giovanna", "Sirati", "Giovy", "sparklingwine");
        Client UZ = new Client("Marco", "Maggi", "Mark", "proseccolover");
        Admin A1 = new Admin("Maria", "Faresi", "Mary", "0FaresiWine1");
        UX.registration(store);
        UY.registration(store);
        UZ.registration(store);
        store.addUser(A1);
        Wine WX = new Wine("Lambrusco Marcello", 2013, "Rosso, Frizzante", "Lambrusco Maestri");
        Wine WY = new Wine("Franciacorta Brut DOCG", 2011, "Champagne, Spumante", "Pinot Nero 22%", "Chardonnay 77%", "Pinot Bianco 1%");
        Bottle BX = new Bottle(WX, 5);
        Bottle BY = new Bottle(WY, 3);
        A1.refillBottle(store, BX);
        A1.refillBottle(store, BY);
        System.out.println(store.toString());

        System.out.println("------ 2a) User UX signs in and buy some of the bottle BX ------");
        UX.login(store, "Jack", "winelover");
        System.out.println("\nCustomer UX searches the the Wine " + BY.getWine().getName() + " of the " + BY.getWine().getYear());
        UX.searchWine(store, WX.getName(), WX.getYear());
        System.out.println("\nThe wine searched is available, so the user decides to buy 4 bottles of the wine");
        UX.buyWine(store, WX, 4);
        A1.shipOrder(store);

        System.out.println("\n------ 2b) User UY signs in and buy all the bottles BY ------");
        UY.login(store, "Giovy", "sparklingwine");
        System.out.println("\nCustomer UY searches the the Wine " + BY.getWine().getName() + " of the " + BY.getWine().getYear());
        UY.searchWine(store, WY.getName(), WY.getYear());
        System.out.println("\nThe wine searched is available, so the user decides to buy 3 bottles of the wine");
        UY.buyWine(store, WY, 3);
        A1.shipOrder(store);

        System.out.println("\n------ 3) User UZ signs and to buy some of the bottle UY now gone out of stock, so ask to be notified when \n" +
                "      the bottle BY comes back available ------\n");
        UZ.login(store, "Mark", "proseccolover");
        System.out.println("\nCustomer UZ searches the the Wine " + BY.getWine().getName() + " of the " + BY.getWine().getYear());
        UZ.searchWine(store, WY.getName(), WY.getYear());
        System.out.println("The bottles of the wine searched by the User UZ are out of stock so he asks for notification\n");
        UZ.askNotification(store, WY, 2);

        System.out.println("------ 4) The employee refill the missing bottles UY and the system send a notification to the user UZ \n" +
                "      about the new availability of the bottle ------");
        Bottle BY1 = new Bottle(WY, 3);
        A1.refillBottle(store, BY1);
    }
}