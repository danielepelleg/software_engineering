package Classes;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creo degli Oggetti Admin, Membri, Corsi e Gare
        Member me = new Member("tommaso", "veltri", "tom", "1234");
        Member you = new Member("gianluca", "vacchi", "vacchigianlu", "1234");
        Member he = new Member("piero", "tuni", "pierino", "1234");
        Member vince = new Member("Vincenzo", "Magri", "Vince", "9876");
        Admin a = new Admin("gino", "paoli", "ginogino", "1234");
        Race ciclismo = new Race("Tour");
        Course yoga = new Course("Gru");

        // Creo le liste di Attività e Persone e inserisco i dati creati
        Person[] persons = new Person[]{a, me, you};
        System.out.println("Password di Tom: " + me.getPassword());
        me.Registration(ciclismo);
        System.out.println(ciclismo.show());
        me.Unsubscribe(ciclismo);
        System.out.println(ciclismo.show());
        /*
        System.out.println("Persone nella lista:");
        for (Person p : persons){
            p.show();}
        persons = a.deleteMember(he, persons);
        System.out.println("Persone nella lista: DeleteMember non Presente");
        for (Person p : persons){
            p.show();}
        Activity[] activities = new Activity[]{ciclismo, yoga};
        a.Registration(ciclismo, me);
        //me.Registration(ciclismo);
        you.Registration(ciclismo);
        System.out.println("Persone Iscritte a Ciclismo - Dopo Iscrizione");
        System.out.println(ciclismo.getPersonArray().length);
        // L'admin crea un nuovo membro e lo inserisce nella lista di persone
        persons = a.addMember(vince, persons);
        System.out.println("Persone nella lista: AddMember");
        for (Person p : persons){
            p.show();
        }
        you.Unsubscribe(ciclismo);
        persons = a.deleteMember(you, persons);
        System.out.println("Persone nella lista: DeleteMember");
        for (Person p : persons){
            p.show();}
        System.out.println("Persone Iscritte a Ciclismo - Dopo Iscrizione");
        System.out.println(ciclismo.getPersonArray().length);
        for (Person i : ciclismo.getPersonArray()){
            System.out.println(i.getUsername());
        }
        System.out.println(Arrays.toString(ciclismo.getPersonArray()));

        Scanner sc = new Scanner(System.in);
        System.out.println("Premi INVIO per continuare");
        sc.nextLine();
        me.Unsubscribe(ciclismo);
        System.out.println("Persone Iscritte a Ciclismo - Dopo Cancellazione (Tom)");
        System.out.println(ciclismo.getPersonArray().length);
        for(int i = 0; i < ciclismo.getPersonArray().length; i++){
            System.out.println(ciclismo.getPersonArray()[i].getUsername());
        }

        System.out.println("Premi INVIO per continuare");
        sc.nextLine();
        you.Unsubscribe(ciclismo);
        System.out.println("Persone Iscritte a Ciclismo - Dopo Cancellazione (Gianlu)");
        System.out.println(ciclismo.getPersonArray().length);
        for(int i = 0; i < ciclismo.getPersonArray().length; i++){
            System.out.println(ciclismo.getPersonArray()[i].getUsername());
        }
         */

    }
}

