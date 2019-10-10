package Classes;

import java.lang.*;

/**
 * Activity Class
 * The class has the name of the activity and an array of person registered to it
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Activity {
    protected String name;
    protected Person[] PersonArray;

    /**
     * Class constructor.
     *
     * @param name the name of the activity to be created
     *
     */
    public Activity(String name){
        this.name = name;
        this.PersonArray = new Person[]{};
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        this.name = n;
    }

    public Person[] getPersonArray() {
        return PersonArray;
    }

    /**
     * Add a new subscriber to the activity
     * The activity must not contain the person to register
     *
     * @param ego the array that contains the person to be added to @PersonArray
     *
     */
    public void Registration(Person ego){
        Person [] p = new Person[]{ego};
        Person[] temp = new Person[getPersonArray().length+p.length];
        boolean check = false;
        for (Person pe : p){
            for (Person pa : getPersonArray()){
                if (pe.getUsername() == pa.getUsername())
                    check = true;
            }
        }
        if (!check){
            System.arraycopy(getPersonArray(), 0, temp, 0, getPersonArray().length);
            System.arraycopy(p, 0, temp, getPersonArray().length, p.length);
            this.PersonArray = temp;
        }
    }

    /**
     * Remove a subscriber from the activity
     * The activity must not be empty
     * The activity must contain the person to unsubscribe
     *
     * @param ego the person to be removed from the activity
     *
     */
    public void Unsubscribe(Person ego){
        if (getPersonArray().length != 0) {
            Person[] temp = new Person[getPersonArray().length - 1];
            int inserted = 0;
            boolean check = false;
            for (Integer i = 0; i < getPersonArray().length; i++) {
                if (getPersonArray()[i].getUsername() == ego.getUsername())
                    check = true;
            }
            if (check) {
                for (Integer i = 0; i < getPersonArray().length; i++) {
                    if (getPersonArray()[i].getUsername() != ego.getUsername()) {
                        System.arraycopy(getPersonArray(), i, temp, inserted, 1);
                        inserted++;
                    }
                }
                this.PersonArray = temp;
            }
        }


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
