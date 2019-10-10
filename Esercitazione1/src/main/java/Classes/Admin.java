package Classes;

public class Admin extends Member {
    public Admin(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    public Person[] addMember (Member m, Person[] people){
        // Creo una variabile booleana che controlla che il membro non sia già
        // presente nella lista di persone in cui lo inserisco
        boolean check = false;
        // Metto l'oggetto m all'interno di un array creato appositamente da utilizzare nell'arraycopy
        Person[] member = new Person[]{m};
        // Creo l'array di persone "temp" che andrà a contenere le  persone
        // prima presenti nella lista e il nuovo oggetto Membro m
        Person[] temp = new Person[people.length+1];
        // Scorro la lista di persone e controllo che l'oggetto Membro m non sia già presente
        // all'interno, se esso è presente allora check = true
        for (Integer i = 0; i < people.length; i++){
            if (people[i].getUsername() == m.getUsername())
                check = true;
        }
        // Se non è presente creo il nuovo array temp composto
        // dall'array persone + array contenente membro
        if (check==false){
            System.arraycopy(people, 0, temp, 0, people.length);
            System.arraycopy(member, 0, temp, people.length, member.length);
            // Ritorno il nuovo array contenente come ultimo elemento m
            return  temp;
        }
        // Se il membro da aggiungere è già presente ritorno l'array iniziale
        else return people;
    }

    public Person[] deleteMember (Member m, Person[] people) {
        boolean check = false;
        for (Person p : people) {
            if (p == m)
                check = true;
        }
        // Se è presente creo il nuovo array temp composto
        // dall'array persone - elemento Membro m
        if (check) {
        	Person[] temp = new Person[people.length - 1];
        	 int person_entered = 0;
            for (Integer i = 0; i < people.length; i++) {
                if (m.getUsername() != people[i].getUsername()) {
                    System.arraycopy(people, i, temp, person_entered, 1);
                    person_entered++;
                }
            }
            // Ritorno il nuovo array non contenente l'elemento m
            return temp;
        }
        // Se il membro da rimuovere non è presente ritorno l'array iniziale
        else return people;
    }

    public Activity[] addActivity(Activity a, Activity[] activities){
        // Creo una variabile booleana che controlla che il membro non sia già
        // presente nella lista di persone in cui lo inserisco
        boolean check = false;
        // Metto l'oggetto a all'interno di un array creato appositamente da utilizzare nell'arraycopy
        Activity[] activity = new Activity[]{a};
        // Creo l'array di activity "temp" che andrà a contenere le  attività
        // prima presenti nella lista e il nuovo oggetto Activity a
        Activity[] temp = new Activity[activities.length+1];
        // Scorro la lista di persone e controllo che l'oggetto Activity a non sia già presente
        // all'interno, se esso è presente allora check = true
        for (Integer i = 0; i < activities.length; i++){
            if (activities[i].getName() != a.getName())
                check = true;
            else check = false;
        }
        // Se non è presente creo il nuovo array temp composto
        // dall'array persone + array contenente membro
        if (check){
            System.arraycopy(activities, 0, temp, 0, activities.length);
            System.arraycopy(activity, 0, temp, activities.length, activity.length);
            // Ritorno il nuovo array contenente come ultimo elemento m
            return  temp;
        }
        // Se il membro da aggiungere è già presente ritorno l'array iniziale
        else return activities;
    }

    public Activity[] deleteActivity(Activity a, Activity[] activities){
        // Creo l'array di attività "temp" che andrà a contenere le Activity
        // prima presenti nella lista activities senza l'oggetto Activity a
        Activity[] temp = new Activity[activities.length - 1];
        // Variabile che conta le Activity inserite in temp per arraycopy
        int inserted = 0;
        // Variabile booleana che controlla che l''Activity a che vado a togliere
        // sia effettivamente presente nella lista di activities
        boolean check = false;
        // Controllo che l'oggetto Activity a sia presente nell'array activities
        for (Activity s : activities) {
            if (s == a)
                check = true;
        }
        // Se è presente creo il nuovo array temp composto
        // dall'array activities - elemento Activity a
        if (check) {
            for (Integer i = 0; i < activities.length; i++) {
                if (a.getName() != activities[i].getName()) {
                    System.arraycopy(activities, i, temp, inserted, 1);
                    inserted++;
                }
            }
            // Ritorno il nuovo array non contenente l'elemento a
            return temp;
        }
        // Se l'attività da rimuovere non è presente ritorno l'array iniziale
        else return activities;
    }

    public void Registration(Activity a, Member m){
        a.Registration(m);
    }

    public void Unsubscribe(Activity a, Member m){
        a.Unsubscribe(m);
    }

    public void editMember(Member m, String n, String s, String u, String p){
        m.setName(n);
        m.setSurname(s);
        m.setUsername(u);
        m.setPassword(p);
    }

    public void EditActivity(Activity a, String n){
        a.setName(n);
    }
}
