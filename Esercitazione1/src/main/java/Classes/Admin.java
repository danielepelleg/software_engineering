package Classes;

public class Admin extends Member {
    public Admin(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    public Person[] addMember (String m_name, String m_surname, String m_username, String m_password, Person[] people){
        // Creo l'oggetto Membro m composto dai dati passati alla funzione
        Member m = new Member(m_name, m_surname, m_username, m_password);
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
            if (people[i].getUsername() != m.getUsername())
                check = true;
            else check = false;
        }
        // Se non è presente creo il nuovo array temp composto
        // dall'array persone + array contenente membro
        if (check){
            System.arraycopy(people, 0, temp, 0, people.length);
            System.arraycopy(member, 0, temp, people.length, member.length);
            // Ritorno il nuovo array contenente come ultimo elemento m
            return  temp;
        }
        // Se il membro da aggiungere è già presente ritorno l'array iniziale
        else return people;
    }

    public Person[] deleteMember (Member m, Person[] people) {
        // Creo l'array di persone "temp" che andrà a contenere le  persone
        // prima presenti nella lista senza l'oggetto Membro m
        Person[] temp = new Person[people.length - 1];
        // Variabile che conta le persone inserite in temp per arraycopy
        int person_entered = 0;
        // Variabile booleana che controlla che il membro che vado a togliere
        // sia effettivamente presente nella lista di persone
        boolean check = false;
        // Controllo che l'oggetto Membro m sia presente nell'array people
        for (Person p : people) {
            if (p == m)
                check = true;
        }
        // Se è presente creo il nuovo array temp composto
        // dall'array persone - elemento Membro m
        if (check) {
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


    public void AddActivity(String a_name, Activity[] activities){
        Activity a = new Activity(a_name);
    }

    public void Registration(Activity a, Member m){
        Person[] p = new Person[]{m};
        a.Registration(p);
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
