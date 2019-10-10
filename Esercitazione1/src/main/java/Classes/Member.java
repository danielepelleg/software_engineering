package Classes;

/**
 * Member Class - Person Subclass
 * Each member has the name attribute, the surname, the username,
 * the password and the hashed password generated from the last one
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Member extends Person {

    /**
     * Class constructor.
     *
     * @param name the name of the member to be created
     * @param surname the surname of the member to be created
     * @param username the username of the member to be created
     * @param password the password of the member to be created
     *
     */
    public Member(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    /**
     * Subscribe the member to an activity.
     *
     * @param a Activity to which to subscribe the member
     *
     */
    public void Subscribe(Activity a){
        a.Registration(Member.this);
    }

    /**
     * Unsubscribe the member to an activity.
     *
     * @param a Activity to which to unsubscribe the member
     *
     */
    public void Unsubscribe(Activity a){
        a.Unsubscribe(Member.this);
    }
}