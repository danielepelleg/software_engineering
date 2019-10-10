package Classes;

/**
 * Admin Class - Member Subclass
 * Each admin has the name attribute, the surname, the username,
 * the password and the hashed password generated from the last one
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Admin extends Member {

    /**
     * Class constructor.
     *
     * @param name the name of the admin to be created
     * @param surname the surname of the admin to be created
     * @param username the username of the admin to be created
     * @param password the password of the admin to be created
     *
     */
    public Admin(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    /**
     * Add a member to an array of Person objects
     * The array must not contain the member to add -> check
     *
     * @param m Member to add to the array
     * @param people Array of Person objects to which to add the member
     *
     * @return Person Array + New Member or Initial Person Array if the member is already inside
     */
    public Person[] addMember (Member m, Person[] people){
        boolean check = false;
        Person[] member = new Person[]{m};
        for (Integer i = 0; i < people.length; i++){
            if (people[i].getUsername() == m.getUsername())
                check = true;
        }
        if (!check){
            Person[] temp = new Person[people.length+1];
            System.arraycopy(people, 0, temp, 0, people.length);
            System.arraycopy(member, 0, temp, people.length, member.length);
            return  temp;
        }
        else return people;
    }

    /**
     * Delete a member from an array of Person objects
     * The array must contain the member to delete -> check
     *
     * @param m Member to add to the array
     * @param people Array of Person objects from which delete the member
     *
     * @return Person Array - Member or Initial Person Array if the member is not inside
     */
    public Person[] deleteMember (Member m, Person[] people) {
        boolean check = false;
        for (Person p : people) {
            if (p == m)
                check = true;
        }
        if (check) {
            Person[] temp = new Person[people.length - 1];
            int person_entered = 0;
            for (Integer i = 0; i < people.length; i++) {
                if (m.getUsername() != people[i].getUsername()) {
                    System.arraycopy(people, i, temp, person_entered, 1);
                    person_entered++;
                }
            }
            return temp;
        }
        else return people;
    }

    /**
     * Add an activity to an array of Activity objects
     * The array must not contain the activity to add -> check
     *
     * @param a Activity to add to the array
     * @param activities Array of Activity objects to which to add the activity
     *
     * @return Activity Array + New Activity or Initial Activity Array if the activity is already inside
     */
    public Activity[] addActivity(Activity a, Activity[] activities){
        boolean check = false;
        Activity[] activity = new Activity[]{a};
        for (Integer i = 0; i < activities.length; i++){
            if (activities[i].getName() == a.getName())
                check = true;
        }
        if (!check){
            Activity[] temp = new Activity[activities.length+1];
            System.arraycopy(activities, 0, temp, 0, activities.length);
            System.arraycopy(activity, 0, temp, activities.length, activity.length);
            return  temp;
        }
        else return activities;
    }

    /**
     * Delete an activity from an array of Activity objects
     * The array must contain the activity to delete -> check
     *
     * @param a Activity to delete from the array
     * @param activities Array of Activity objects from which delete the activity
     *
     * @return Activity Array - Activity or Initial Activity Array if the activity is not inside
     */
    public Activity[] deleteActivity(Activity a, Activity[] activities){
        boolean check = false;
        for (Activity s : activities) {
            if (s == a)
                check = true;
        }
        if (check) {
            Activity[] temp = new Activity[activities.length - 1];
            int inserted = 0;
            for (Integer i = 0; i < activities.length; i++) {
                if (a.getName() != activities[i].getName()) {
                    System.arraycopy(activities, i, temp, inserted, 1);
                    inserted++;
                }
            }
            return temp;
        }
        else return activities;
    }

    /**
     * Subscribe a member to an activity
     *
     * @param a Activity to which to subscribe the member
     * @param m Member to subscribe
     *
     */
    public void Subscribe(Activity a, Member m){
        a.Registration(m);
    }

    /**
     * Unsubscribe a member to an activity
     *
     * @param a Activity to which to unsubscribe the member
     * @param m Member to unsubscribe
     *
     */
    public void Unsubscribe(Activity a, Member m){
        a.Unsubscribe(m);
    }

    /**
     * Edit the parameters of a member
     *
     * @param m Member to edit
     * @param n new Name
     * @param s new Surname
     * @param u new Username
     * @param p new Password
     *
     */
    public void editMember(Member m, String n, String s, String u, String p){
        m.setName(n);
        m.setSurname(s);
        m.setUsername(u);
        m.setPassword(p);
        m.setHashedPassword(p);
    }

    /**
     * Edit the parameters of an activity
     *
     * @param a Activity to edit
     * @param n new Name
     *
     */
    public void editActivity(Activity a, String n){
        a.setName(n);
    }
}
