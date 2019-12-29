package SportClub;

import Database.DatabaseManager;

/**
 * SportClub.Admin Class - SportClub.Member Subclass
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
     */
    public Admin(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Sign the admin in.
     */
    @Override
    public boolean login(){
        return DatabaseManager.authenticate(this, true);
    }

    /**
     * Add a member in the member table on the database.
     */
    public void addMember (Member newMember){
        DatabaseManager.register(newMember);
    }

    /**
     * Remove a member from member table on the database.
     */
    public void removeMember (Member member) {
        DatabaseManager.delete(member);
    }

    /**
     * Add a new activity in the database.
     *
     * @param newActivity the activity to add.
     */
    public void addActivity(Activity newActivity){
        DatabaseManager.addActivity(newActivity);
    }

    /**
     * Remove an activity from the database.
     *
     * @param activity the activity to add.
     */
    public void deleteActivity(Activity activity){
        DatabaseManager.deleteActivity(activity);
    }

    /**
     * Subscribe a member to an activity
     *
     * @param activity SportClub.Activity to which to subscribe the member
     * @param member SportClub.Member to subscribe
     *
     */
    public void subscribe(Activity activity, Member member){
        DatabaseManager.subscribe(activity, member);
    }

    /**
     * Unsubscribe a member to an activity
     *
     * @param activity SportClub.Activity to which to unsubscribe the member
     * @param member SportClub.Member to unsubscribe
     *
     */
    public void unsubscribe(Activity activity, Member member){
        DatabaseManager.unsubscribe(activity, member);
    }

    /**
     * Edit the parameters of a member
     *
     * @param member SportClub.Member to edit
     * @param newName new Name
     * @param newSurname new Surname
     * @param newUsername new Username
     * @param newPassword new Password
     *
     */
    public void editMember(Member member, String newName, String newSurname, String newUsername, String newPassword){
        DatabaseManager.editMember(member, newName, newSurname, newUsername, newPassword);
    }

    /**
     * Edit the parameters of an activity
     *
     * @param activity SportClub.Activity to edit
     * @param newName new Name
     *
     */
    public void editActivity(Activity activity, String newName){
        DatabaseManager.editActivity(activity, newName);
    }
}