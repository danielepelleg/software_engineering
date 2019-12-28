package SportClub;

/**
 * SportClub.Activity Class
 * The class has the name of the activity and an array of person registered to it
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public abstract class Activity {
    private String name;
    private Person[] PersonArray;

    /**
     * Class constructor.
     *
     * @param name the name of the activity to be created
     *
     */
    public Activity(String name){
        this.name = name;
        this.PersonArray = new Person[]{};
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        this.name = n;
    }

    public Person[] getPersonArray() {
        return PersonArray;
    }

    /**
     * Edit the attributes.
     *
     * @param newName new Name
     */
    public void edit(String newName){
        this.setName(newName);
    }

    /**
     * Return a string showing activity's
     * number of subscribers and their username
     *
     * @return String the string
     *
     */
    public String show(){
        String s = " Name: '" + this.getName() + "'" + ", Subscribers: '" + this.getPersonArray().length + "' \n";
        if (getPersonArray().length != 0){
            s += " Sub. Username: \n";
            for (Person p : getPersonArray()){
                s += " " + p.getUsername() + "\n";
            }
        }
        else s += " \t\tThere are no subscribers here. ";
        return  s;
    }
}
