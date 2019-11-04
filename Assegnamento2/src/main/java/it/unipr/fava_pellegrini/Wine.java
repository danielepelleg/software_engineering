package it.unipr.fava_pellegrini;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Wine Class
 *
 * Each wine has the name, the year of production, the data sheet and a list
 * containing the vineyard from which it comes from
 *
 */
public class Wine {
    private String name;
    private int year;
    private String notes;
    private ArrayList<String>  vineyards = new ArrayList<String>();

    /**
     * This constructor generates an empty Wine object.
     *
     */
    public Wine() {
        this.name = "";
        this.year = 1900;
        this.notes = "";
        this.vineyards = new ArrayList<String>();
    }

    /**
     * This constructor generates a Wine object from its parameters.
     *
     * @param name the name of the wine
     * @param year the year of production
     * @param notes a description of the wine
     * @param vineyards a list of String containing the vineyards
     *
     */
    public Wine(String name, int year, String notes, ArrayList<String>  vineyards) {
        this.name = name;
        this.year = year;
        this.notes = notes;
        this.vineyards = vineyards;
    }

    /**
     * Class Constructor
     *
     * @param name the name of the wine
     * @param year the year of production
     * @param notes a description of the wine
     * @param vineyards a list of String containing the vineyards
     *
     */
    public Wine(String name, int year, String notes, String... vineyards) {
        this.name = name;
        this.year = year;
        this.notes = notes;
        this.vineyards.addAll(Arrays.asList(vineyards));
    }

    /**
     * Class Constructor
     *
     * @param name the name of the wine
     * @param year the year of production
     * @param notes a description of the wine
     *
     */
    public Wine(String name, int year, String notes) {
        this.name = name;
        this.year = year;
        this.notes = notes;
        this.vineyards = new ArrayList<String>();
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

    public ArrayList<String> getVineyards() {
        return vineyards;
    }

    public void setVineyards(ArrayList<String> vines) {
        this.vineyards = vines;
    }

    /**
     * Add a new vineyard to the vineyards list
     *
     * @param newVineyard the vineyard to add
     */
    public void addVinewyard(String newVineyard){
        this.vineyards.add(newVineyard);
    }

    /**
     * Return a string showing the wine's
     * name, year, notes and prints the list of vineyards
     *
     * @return String the string
     *
     */
    @Override
    public String toString(){
        String show = " Name: " + getName() + "\n Year: " + getYear() + "\n Notes: " + getNotes() + "\n Vines List:";
        int index = 0;
        for (String s : getVineyards()){
            if (index != getVineyards().size()-1) {
                show += " " + s + ",";
                index++;
            }
            else show += " " + s + "\n";
        }
        return show;
    }
}
