package it.unipr.fava_pellegrini;

public class Order {
    Client buyer;
    Wine wineChosen;
    int bottleAmount;

    Order(Client buyer, Wine wineChosen, int wineAmount){
        this.buyer = buyer;
        this.wineChosen = wineChosen;
        this.bottleAmount = wineAmount;
    }

    @Override
    public String toString(){
        return "Client:\n" + this.buyer.toString() + "\n\nWine Chosen:\n" + this.wineChosen.toString() + "\n" + "Quantity: " + this.bottleAmount;
    }
}
