package it.unipr.fava_pellegrini;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Winehouse {
    public ArrayList<Wine> wines;
    public ArrayList<Person> users;
    public LinkedHashMap<Person, List<Wine>> orders;

    Winehouse(){
        this.wines = new ArrayList<Wine>();
        this.users = new ArrayList<Person>();
        this.orders = new LinkedHashMap<Person, List<Wine>>();
    };

    public ArrayList<Wine> getWines() {
        return wines;
    }

    public void setWines(ArrayList<Wine> wines) {
        this.wines = wines;
    }

    public ArrayList<Person> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Person> users) {
        this.users = users;
    }

    public LinkedHashMap<Person, List<Wine>> getOrders() {
        return orders;
    }

    public void setOrders(LinkedHashMap<Person, List<Wine>> orders) {
        this.orders = orders;
    }

    public void Registration(Person newPerson){
        getUsers().add(newPerson);
    }

    public void addWine(Wine newWine){
        wines.add(newWine);
    }

    public List<Wine> searchWine(String n, int y) {
        ArrayList<Wine> result = new ArrayList<Wine>();
        for (Wine w : getWines()) {
            if (w.getName().equals(n) && w.getYear() == y)
                result.add(w);
        }
        return result;
    }
}
