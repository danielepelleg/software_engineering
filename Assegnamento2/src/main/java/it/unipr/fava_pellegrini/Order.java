package it.unipr.fava_pellegrini;

public class Order {
    Client buyer;
    Wine wineChosen;
    int wineAmount;

    Order(Client buyer, Wine wineChosen, int wineAmount){
        this.buyer = buyer;
        this.wineChosen = wineChosen
        this.wineAmount = wineAmount;
    }
}
