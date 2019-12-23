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
     * Class constructor for login.
     *
     * @param username the username of the member to be created
     * @param password the password of the member to be created
     */
    public Member(String username, String password){
        super(username, password);
    }

    /**
     * Register a member in the database.
     */
    public void register(){
        DatabaseManager.register(this);
    }

    /**
     * Sign the member in.
     */
    public void login(){
        DatabaseManager.login(this);
    }

    /**
     * Subscribe the member to an activity.
     *
     * @param activity Activity to which to subscribe the member
     */
    public void subscribe(Activity activity){
        DatabaseManager.subscribe(activity, this);
    }

    /**
     * Unsubscribe the member to an activity.
     *
     * @param activity Activity to which to unsubscribe the member
     */
    public void unsubscribe(Activity activity){
        DatabaseManager.unsubscribe(activity, this);
    }
}