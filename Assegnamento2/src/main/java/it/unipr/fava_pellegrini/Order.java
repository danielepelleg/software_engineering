package it.unipr.fava_pellegrini;

/**
 * Order Class
 * Each order has the relative Client's object, the bottle chosen by the client,
 * a boolean value which tells if is processed and a notification showing if the
 * the client has activated the notification of availability
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Order {
    private Client buyer;
    private Bottle orderBottle;
    private boolean processed;
    private boolean notification;

    /**
     * Class constructor.
     *
     * @param buyer the order's relative client
     * @param wineAmount the wine chosen
     * @param wineChosen the quantity of of wine chosen
     *
     */
    Order(Client buyer, Wine wineChosen, int wineAmount){
        this.buyer = buyer;
        this.orderBottle = new Bottle(wineChosen, wineAmount);
        this.processed = false;
        this.notification = false;
    }

    public Client getBuyer() {
        return buyer;
    }

    public void setBuyer(Client buyer) {
        this.buyer = buyer;
    }

    public Bottle getOrderBottle() {
        return orderBottle;
    }

    public void setOrderBottle(Bottle orderBottle) {
        this.orderBottle = orderBottle;
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

    /**
     * Return a string showing the order status
     * true if processed true, false otherwise
     *
     * @param processed the boolean value of the attribute
     *
     * @return the string of the relative boolean value
     *
     */
    public String status(boolean processed){
        if(processed)
            return "true";
        else return "false";
    }

    /**
     * Return a string showing the order's summary
     *
     * @return String the string
     *
     */
    @Override
    public String toString(){
        String show = "Client:\n" + this.buyer.toString() + "\n\nBottle Chosen:\n" + this.orderBottle.toString()
                + "\n Processed: " + status(this.processed) + "\n";
        if (isNotification())
            return show += "\nNotification: " + status(this.notification);
        else return show;
    }
}
