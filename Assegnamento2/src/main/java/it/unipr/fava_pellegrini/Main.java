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
        UX.Registration(store);
        UY.Registration(store);
        UZ.Registration(store);
        store.Registration(A1);
        Wine WX = new Wine("Lambrusco Marcello", 2013, "Rosso, Frizzante", "Lambrusco Maestri");
        Wine WY = new Wine("Franciacorta Brut DOCG", 2011, "Champagne, Spumante", "Pinot Nero 22%", "Chardonnay 77%", "Pinot Bianco 1%");
        Bottle BX = new Bottle(WX, 5);
        Bottle BY = new Bottle(WY, 3);
        A1.refillBottle(store, BX, 0);
        A1.refillBottle(store, BY, 0);
        System.out.println(store.toString());

        System.out.println("------ 2a) User UX signs in and buy some of the bottle BX ------");
        UX.Login(store, "Jack", "winelover");
        System.out.println("\nCustomer UX: Buying 4 bottles of the Wine " + BX.getWine().getName());
        UX.buyWine(store, WX, 4);
        System.out.println("The admin A1 checks the requests in the orders list and sell the wine to the customer UX:");
        A1.sellWine(store);

        System.out.println("\n------ 2b) User UY signs in and buy all the bottles BY ------");
        UY.Login(store, "Giovy", "sparklingwine");
        System.out.println("\nCustomer UY: Buying all the 3 bottles of the Wine " + BY.getWine().getName());
        UY.buyWine(store, WY, 3);
        System.out.println("The admin A1 checks the requests in the orders list and sell the wine to the customer UY:");
        A1.sellWine(store);

        System.out.println("\n\n------ 3) User UZ signs to buy some of the bottle UY now gone out of stock, so ask to be notified when \n" +
                "      the bottle BY comes back available ------");
        UZ.Login(store, "Mark", "proseccolover");
        UZ.buyWine(store, WY, 2);
        A1.sellWine(store);

        System.out.println("------ 4) The employee refill the missing bottles UY and the system send a notification to the user UZ \n" +
                "      about the new availability of the bottle ------");
        A1.refillBottle(store, BY, 2);
        A1.sendNotification(store);
    }
}