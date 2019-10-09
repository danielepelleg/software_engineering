package Classes;
import java.lang.*;

import java.lang.reflect.Array;

public class Activity {
    private String name;
    private Person[] PersonArray;

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

    public void Registration(Person[] p){
        Person[] temp = new Person[getPersonArray().length+p.length];
        System.arraycopy(getPersonArray(), 0, temp, 0, getPersonArray().length);
        System.arraycopy(p, 0, temp, getPersonArray().length, p.length);
        this.PersonArray = temp;
    }

    public void Unsubscribe(Person ego){
        Person[] temp = new Person[getPersonArray().length-1];
        int person_entered = 0;
        for (Integer i = 0; i < getPersonArray().length; i++){
            if (getPersonArray()[i].getUsername() != ego.getUsername()){
                System.arraycopy(getPersonArray(), i, temp, person_entered,1);
                person_entered++;
            }
        }
        this.PersonArray = temp;

    }
}
