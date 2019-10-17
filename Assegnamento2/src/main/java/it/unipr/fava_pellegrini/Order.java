package it.unipr.fava_pellegrini;

public class Order {
    private Client buyer;
    private Wine wineChosen;
    private int bottleAmount;
    private boolean processed;
    private boolean notification;

    Order(Client buyer, Wine wineChosen, int wineAmount){
        this.buyer = buyer;
        this.wineChosen = wineChosen;
        this.bottleAmount = wineAmount;
        this.processed = false;
        this.notification = false;
    }

    public Client getBuyer() {
        return buyer;
    }

    public void setBuyer(Client buyer) {
        this.buyer = buyer;
    }

    public Wine getWineChosen() {
        return wineChosen;
    }

    public void setWineChosen(Wine wineChosen) {
        this.wineChosen = wineChosen;
    }

    public int getBottleAmount() {
        return bottleAmount;
    }

    public void setBottleAmount(int bottleAmount) {
        this.bottleAmount = bottleAmount;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public String status(boolean processed){
        if(processed)
            return "true";
        else return "false";
    }

    @Override
    public String toString(){
        String show = "Client:\n" + this.buyer.toString() + "\n\nWine Chosen:\n" + this.wineChosen.toString() + "\n" + "Quantity: " + this.bottleAmount
                + "\nProcessed: " + status(this.processed) + "\n";
        if (isNotification())
            return show += "\nNotification: " + status(this.notification) + "\n";
        else return show;
    }
}
