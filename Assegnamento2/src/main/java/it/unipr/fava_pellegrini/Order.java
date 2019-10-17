package it.unipr.fava_pellegrini;

public class Order {
    Client buyer;
    Wine wineChosen;
    int bottleAmount;
    boolean processed;

    Order(Client buyer, Wine wineChosen, int wineAmount){
        this.buyer = buyer;
        this.wineChosen = wineChosen;
        this.bottleAmount = wineAmount;
        processed = false;
    }
}
