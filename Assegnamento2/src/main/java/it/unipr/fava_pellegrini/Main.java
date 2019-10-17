package it.unipr.fava_pellegrini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Class
 * Test, through a simulation, the classes and methods created
 *
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Winehouse house = new Winehouse();
        List<String> vines_w1 = new ArrayList<String>();
        vines_w1.add("Gingio");
        vines_w1.add("Giangio");
        Wine w1 = new Wine("Nero", 1989, "Buono", vines_w1, 3);
        Wine w2 = new Wine("Bianco", 1999, "Cattivo", vines_w1, 2);
        Client c1 = new Client("Tommaso", "Gaspari", "Tommy", "1234");
        c1.Registration(house);
        Bottle b1 = new Bottle(w1, 4);
        Bottle b2 = new Bottle(w2, 2);
        house.addBottle(b1);
        house.addBottle(b2);
        //house.addWine(w3);
        System.out.println(c1.searchWine(house, w1.getName(), w1.getYear()));
        System.out.println(c1.searchWine(house, w2.getName(), w2.getYear()));
        c1.buyWine(house, w1, 8);

    }
}