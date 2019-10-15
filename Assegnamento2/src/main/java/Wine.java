import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Wine {
    private String name;
    private int year;
    private String notes;
    private List<String>  vines = new ArrayList<String>();

    public Wine(String name, int year, String notes, List<String>  vines) {
        this.name = name;
        this.year = year;
        this.notes = notes;
        this.vines = vines;
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

    public List<String> getVines() {
        return vines;
    }

    public void setVines(List<String> vines) {
        this.vines = vines;
    }

    @Override
    public String toString(){
        String show = " Name: " + getName() + " Year: " + getYear() + " Notes: " + getNotes() + "\n Vines List:\n";
        for (String s : getVines()){
            show += s + "\n";
        }
        return show;
    }
}
