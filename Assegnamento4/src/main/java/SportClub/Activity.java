package SportClub;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * SportClub.Activity Class
 * The class has the name of the activity and an array of person registered to it
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public abstract class Activity {
    private final StringProperty name;
    private Person[] PersonArray;

    /**
     * Class constructor.
     *
     * @param name the name of the activity to be created
     *
     */
    public Activity(String name){
        this.name = new SimpleStringProperty(name);
        this.PersonArray = new Person[]{};
    }

    public String getName() {
        return name.get();
    }

    public Person[] getPersonArray() {
        return PersonArray;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPersonArray(Person[] personArray) {
        PersonArray = personArray;
    }

    /**
     * Edit the attributes.
     *
     * @param newName new Name
     */
    public void edit(String newName){
        this.setName(newName);
    }

    /**
     * Return a string showing activity's
     * number of subscribers and their username
     *
     * @return String the string
     *
     */
    public String show(){
        String s = " Name: '" + this.getName() + "'" + ", Subscribers: '" + this.getPersonArray().length + "' \n";
        if (getPersonArray().length != 0){
            s += " Sub. Username: \n";
            for (Person p : getPersonArray()){
                s += " " + p.getUsername() + "\n";
            }
        }
        else s += " \t\tThere are no subscribers here. ";
        return  s;
    }
}
