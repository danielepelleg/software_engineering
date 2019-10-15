import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Winehouse {
    private ArrayList<Wine> wines;
    private ArrayList<Person> users;
    private LinkedHashMap<Person, List<Wine>> orders;

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

    public Winehouse(){
        ArrayList<Wine> wines = new ArrayList<Wine>();
        ArrayList<Person> users = new ArrayList<Person>();
        LinkedHashMap<Person, List<Wine>> orders = new LinkedHashMap<Person, List<Wine>>();
    };

    public List<Wine> searchWine(String n, int y) {
        ArrayList<Wine> result = new ArrayList<Wine>();
        for (Wine w : getWines()) {
            if (w.getName().equals(n) && w.getYear() == y)
                result.add(w);
        }
        return result;
    }

    public void Registration(Person p){
        getUsers().add(p);
    }
}
