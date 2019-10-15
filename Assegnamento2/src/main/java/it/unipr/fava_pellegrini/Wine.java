package it.unipr.fava_pellegrini;

import java.util.ArrayList;
import java.util.List;

/**
 * Wine Class
 * Each wine has the name, the year of production, the data sheet, a list
 * containing the vineyard from which it comes from, and the available amount.
 *
 */
public class Wine {
    private String name;
    private int year;
    private String notes;
    private List<String>  vineyards = new ArrayList<String>();
    private int amount;

    public Wine(String name, int year, String notes, List<String>  vines) {
        this.name = name;
        this.year = year;
        this.notes = notes;
        this.vineyards = vines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getVineyards() {
        return vineyards;
    }

    public void setAmount(int amount) {this.amount = amount; }

    public int getAmount() { return amount; }

    public void setVineyards(List<String> vines) {
        this.vineyards = vines;
    }

    @Override
    public String toString(){
        String show = "Name: " + getName() + " Year: " + getYear() + " Notes: " + getNotes() + "\n Vines List:\n";
        for (String s : getVineyards()){
            show += " " + s + "\n";
        }
        return show += " Amount: " + getAmount() + "\n";
    }
}