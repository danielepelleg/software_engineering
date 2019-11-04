package it.unipr.fava_pellegrini;

import java.util.Random;

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
    private String trackingNumber;
    private boolean processed;
    private boolean notification;
    public boolean shipped;

    /**
     * This constructor generates an empty Order object
     *
     */
    public Order () {
        this.buyer = new Client();
        this.orderBottle = new Bottle();
        this.processed = false;
        this.notification = false;
        this.shipped = false;
    }

    /**
     * This constructor generates an Order object from its parameters.
     *
     * @param buyer the order's relative client
     * @param wineAmount the wine chosen
     * @param wineChosen the quantity of of wine chosen
     *
     */
    public Order(Client buyer, Wine wineChosen, int wineAmount){
        this.buyer = buyer;
        this.orderBottle = new Bottle(wineChosen, wineAmount);
        this.processed = false;
        this.notification = false;
        this.shipped = false;
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

    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Generates a random tracking number, a string of Char and Integer
     */
    public void setTrackingNumber() {
        Random random = new Random();
        String code = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(Integer i = 0; i < 13; i++ ){
            if (i == 0 || i == 1 || i == 11 || i == 12)
                code += alphabet.charAt(random.nextInt(alphabet.length()));
            else code += random.nextInt(10);
        }
        this.trackingNumber = code;
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

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
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
            return show += "Notification: " + status(this.notification);
        else return show;
    }
}
