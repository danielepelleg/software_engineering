package it.unipr.fava_pellegrini;

public class Bottle {
    private Wine wine;
    private int bottleAmount;

    Bottle(Wine wine, int bottleAmount){
        this.wine = wine;
        this.bottleAmount = bottleAmount;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public void setBottleAmount(int bottleAmount) {this.bottleAmount = bottleAmount; }

    public int getBottleAmount() { return bottleAmount; }

    public String toString(){
        return this.wine.toString() + "Quantity: " + this.bottleAmount + "\n";
    }
}
