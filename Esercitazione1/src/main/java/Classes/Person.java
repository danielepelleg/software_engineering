package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Person Class
 * Each person has the name attribute, the surname, the username,
 * the password and the hashed password
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Person{
    private String name;
    private String  surname;
    private String username;
    private String password;
    private String hashed_password;

    /**
     * Class constructor.
     *
     * Once the Constructor has given the attributes it generates the hashed
     * password calling the relative method
     *
     * @param name the name of the person to be created
     * @param surname the surname of the person to be created
     * @param username the username of the person to be created
     * @param password the password of the person to be created
     *
     */
    public Person(String name, String surname, String username, String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        setHashedPassword(this.password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the Person's hashed password
     * A message digest is a cryptographic hash function containing a string of digits created by a one-way hashing formula.
     * This one use the MD5 hash cryptographic function, a simple one
     * The message digest takes the string password bytes
     * Then get the hash's bytes and through a for cycle convert it from decimal format to hexadecimal format
     *
     * The result is a complete hashed password in hex format, given to the class attribute 'hashed_password'
     * The operation of encryption is managed inside a try-catch
     *
     * @param password for the Person.
     *
     */
    public void setHashedPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            this.hashed_password = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getUsername(){
        return username;
    }

    /**
     *
     * @return String Person's hashed password
     */
    public String getPassword(){
        return hashed_password;
    }

    /**
     * Return a string showing person's
     * name, surname and username
     *
     * @return String the string
     *
     */
    public String show(){
        return "Name: \t" + this.getName() + "\t Surname: \t" + this.getSurname() + "\t Username: " + this.getUsername();
    }
}
