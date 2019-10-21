package it.unipr.fava_pellegrini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Class
 * Test, through a simulation, the classes and methods created
 *
 * 1) The Winehouse is initialized with some users, an admin and some wines
 * 2) User UX signs in and buy some of the bottle BX
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
        A1.refillBottle(store, BX);
        A1.refillBottle(store, BY);
        System.out.println(store.toString());

        System.out.println("------ 2) User UX signs in and buy some of the bottle BX ------");
        UX.Login(store, "Jack", "winelover");
        System.out.println("\nCustomer UX: Buying 4 bottle of the Wine " + BX.getWine().getName());
        UX.buyWine(store, WX, 4);
        System.out.println("The admin A1 checks the requests in the order list and sell the wine to the customer UX:");
        A1.sellWine(store);

        // TODO Implementing Main
        /*Winehouse house = new Winehouse();
        List<String> vines_w1 = new ArrayList<String>();
        vines_w1.add("Gingio");
        vines_w1.add("Giangio");
        Wine w1 = new Wine("Nero", 1989, "Buono", vines_w1);
        Wine w2 = new Wine("Bianco", 1999, "Cattivo", vines_w1);
        Client c1 = new Client("Tommaso", "Gaspari", "Tommy", "1234");
        Admin a1 = new Admin("Tommaso", "Gaspari", "Tommy", "1234");
        c1.Registration(house);
        Bottle b1 = new Bottle(w1, 4);
        Bottle b2 = new Bottle(w2, 2);
        a1.refillBottle(house, b1);
        //System.out.println(c1.searchWine(house, w1.getName(), w1.getYear()));
        //System.out.println(c1.searchWine(house, w2.getName(), w2.getYear()));
        c1.buyWine(house, w1, 5);
        c1.Login(house, "Tommy", "1234");
        c1.buyWine(house, w1, 5);
        a1.sellWine(house);
        System.out.println(c1.showCart());
        a1.refillBottle(house, b1);
        //System.out.println(house.printBottles());*/
    }
}