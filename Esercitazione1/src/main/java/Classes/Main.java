package Classes;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creo degli Oggetti Admin, Membri, Corsi e Gare
        Member me = new Member("tommaso", "veltri", "tom", "1234");
        Member you = new Member("luca", "vacchi", "vacchigianlu", "1234");
        Member he = new Member("piero", "tuni", "pierino", "1234");
        Admin a = new Admin("gino", "paoli", "ginogino", "1234");
        Race ciclismo = new Race("Tour");
        Course yoga = new Course("Gru");

        // Creo le liste di Attività e Persone e inserisco i dati creati
        Person[] persons = new Person[]{a, me, you};
        for (Person p : persons){
            System.out.println(p.show());
        }
        Activity[] activities = new Activity[]{ciclismo, yoga};
        System.out.println("Password di Tom: " + me.getPassword());

        //Un socio si registra in un attivit�
        me.Subscribe(ciclismo);
        //Un admin registra un altro socio in un attivit�
        a.Subscribe(ciclismo, you);
        System.out.println(ciclismo.show());
        //Un socio si disinscrive da un attivit�
        me.Unsubscribe(ciclismo);
        //Un admin disiscrive un socio da un attivit�
        a.Unsubscribe(ciclismo, you);
        System.out.println(ciclismo.show());

        //Un admin si registra in un attivita
        a.Subscribe(yoga);
        System.out.println(yoga.show());
        //Un admin si disiscrive dall'attivit�
        a.Unsubscribe(yoga);
        System.out.println(yoga.show());

        //Creo un nuovo socio da aggiungere alla lista di persone del club
        Member vince = new Member("Vincenzo", "Magri", "Vince", "9876");
        //L'admin inserisce il nuovo socio nell'array persons
        persons = a.addMember(vince, persons);
        for (Person p : persons){
            System.out.println(p.show());
        }
        System.out.println("\n");
        //Un admin modifica i dati di un socio
        a.editMember(vince, "Marco", "Rossi", "Mark12", "1234");
        for (Person p : persons){
            System.out.println(p.show());
        }
        System.out.println("\n");
        //Un admin elimina il socio appena creato dall'array persons
        persons = a.deleteMember(vince, persons);
        for (Person p : persons){
            System.out.println(p.show());
        }
        System.out.println("\n");

        //Creo una nuova attivit� da aggiungere alla lista di attivit� del club
        Race motorRace = new Race("GrandPrix F1");
        //L'admin inserisce l'attivit� nell'array activities
        activities = a.addActivity(motorRace, activities);
        for (Activity x : activities){
            System.out.println(x.show());
        }
        //L'admin modifica il nome dell'activity
        a.editActivity(motorRace, "GrandPrix Gp");
        for (Activity x : activities){
            System.out.println(x.show());
        }
        //Un admin elimina l'attivit� appena creata dall'array activities
        activities = a.deleteActivity(motorRace, activities);
        for (Activity x : activities){
            System.out.println(x.show());
        }



    }
}
