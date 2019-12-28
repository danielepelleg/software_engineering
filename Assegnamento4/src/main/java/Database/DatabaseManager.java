package Database;
import SportClub.*;


import java.sql.*;

/**
 * Database.DatabaseManager Class
 * Handles the connection with the MySQL Database.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public abstract class DatabaseManager {
    private static final String DBURL =
            "jdbc:mysql://localhost:3306/sportclub?";
    private static final String ARGS =
            "createDatabaseIfNotExist=true&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    public static Connection conn = null;

    public DatabaseManager(){}

    /**
     * @return the connection to the Database
     */
    public static Connection getConnection()
    {
        if (conn != null) return conn;
        return connect();
    }

    /**
     * Connect to the database.
     * @return the connection
     */
    public static Connection connect(){
        try
        {
            Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);
            return conn;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Check if a member is already registered in the database.
     *
     * @param member the member to check
     * @return true if present, false if not present.
     */
    public static boolean checkMember(Member member){
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rset = null;
            if(member.getClass().equals(Member.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.member WHERE username = '" + member.getUsername() + "'");
            if(member.getClass().equals(Admin.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.administrator WHERE username = '" + member.getUsername() + "'");
            while(rset.next() && rset != null)
            {
                String number = rset.getString("total");
                int recurrence = Integer.parseInt(number);
                if (recurrence >= 1){
                    return true;
                }
                if (recurrence == 0){
                    return false;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
            System.exit(0);
        }
        return true;
    }

    /**
     * Check if an activity is already registered in the database.
     *
     * @param activity the activity to check
     * @return true if present, false if not present.
     */
    public static boolean checkActivity(Activity activity){
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rset = null;
            if(activity.getClass().equals(Course.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.course WHERE name = '" + activity.getName() + "'");
            if(activity.getClass().equals(Race.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.race WHERE name = '" + activity.getName() + "'");
            while(rset.next() && rset != null)
            {
                String number = rset.getString("total");
                int recurrence = Integer.parseInt(number);
                if (recurrence >= 1){
                    return true;
                }
                if (recurrence == 0){
                    return false;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
            System.exit(0);
        }
        return true;
    }

    /**
     * Check if a member is already subscribed to an activity.
     *
     * @param username the username of the member to check
     * @return true if already subscribed, false if not.
     */
    public static boolean checkSubscription(Activity activity, String username){
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rset = null;
            if(activity.getClass().equals(Course.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.activity_course WHERE member_username = '" + username + "' AND course_name = '" + activity.getName() + "'");
            if(activity.getClass().equals(Race.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.activity_race WHERE member_username = '" + username + "' AND race_name = '" + activity.getName() + "'");
            while(rset.next() && rset != null)
            {
                String number = rset.getString("total");
                int recurrence = Integer.parseInt(number);
                if (recurrence >= 1){
                    return true;
                }
                if (recurrence == 0){
                    return false;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
            System.exit(0);
        }
        return true;
    }

    /**
     * Register a member in the database.
     *
     * @param member the member to register
     */
    public static void register(Member member){
        if (!checkMember(member)){
            try {
                Statement stmt = getConnection().createStatement();
                if (member.getClass().equals(Member.class))
                    stmt.executeUpdate("insert into sportclub.member(name, surname, username, hashed_password) VALUES ('"+ member.getName() + "', '" + member.getSurname() +"', '" + member.getUsername() + "', '"+ member.getPassword() +"')");
                else if (member.getClass().equals(Admin.class))
                    stmt.executeUpdate("insert into sportclub.administrator(name, surname, username, hashed_password) VALUES ('"+ member.getName() + "', '" + member.getSurname() +"', '" + member.getUsername() + "', '"+ member.getPassword() +"')");
                System.out.println("done");
            }
            catch(SQLException e)
            {
                System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
                System.exit(0);
            }
        }
        else System.out.println("This user is already registered in the database!");
    }

    /**
     * Delete a member from the database.
     *
     * @param member member to delete
     */
    public static void delete(Member member){
        if(checkMember(member)){
            try{
                Statement stmt = getConnection().createStatement();
                if (member.getClass().equals(Member.class))
                    stmt.executeUpdate("delete from sportclub.member WHERE username = '" + member.getUsername() + "'");
                else if (member.getClass().equals(Admin.class))
                    stmt.executeUpdate("delete from sportclub.administrator WHERE username = '" + member.getUsername() + "'");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else System.out.println("You can't delete member who is not registered!");
    }

    /**
     * Add an activity in the database.
     */
    public static void addActivity(Activity activity){
        if (!checkActivity(activity)){
            try {
                Statement stmt = getConnection().createStatement();
                if (activity.getClass().equals(Course.class))
                    stmt.executeUpdate("insert into sportclub.course(name) VALUES ('"+ activity.getName() + "')");
                else if (activity.getClass().equals(Race.class))
                    stmt.executeUpdate("insert into sportclub.race(name) VALUES ('"+ activity.getName() + "')");
                System.out.println("done");
            }
            catch(SQLException e)
            {
                System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
                System.exit(0);
            }
        }
        else System.out.println("This activity is already registered in the database!");
    }

    /**
     * Delete an activity from the database.
     */
    public static void deleteActivity(Activity activity){
        if (checkActivity(activity)){
            try {
                Statement stmt = getConnection().createStatement();
                if (activity.getClass().equals(Course.class))
                    stmt.executeUpdate("delete from sportclub.course WHERE name = '" + activity.getName() + "'");
                else if (activity.getClass().equals(Race.class))
                    stmt.executeUpdate("delete from sportclub.race WHERE name = '" + activity.getName() + "'");
                System.out.println("done");
            }
            catch(SQLException e)
            {
                System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
                System.exit(0);
            }
        }
        else System.out.println("Error while deleting activity. This activity is not present in the database.");
    }

    public static boolean authenticate(String username, String password, boolean isAdmin) {
        try {
            PreparedStatement pstmt;
            if (!isAdmin)
                pstmt = getConnection().prepareStatement("SELECT  * FROM sportclub.member WHERE username = ? AND hashed_password = ?");
            else
                pstmt = getConnection().prepareStatement("SELECT  * FROM sportclub.administrator WHERE username = ? AND hashed_password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Sign the member or the admin in.
     */
    public static void login(Member member){
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rset = null;
            if (member.getClass().equals(Member.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.member WHERE username = '" + member.getUsername() + "' AND hashed_password = '" + member.getPassword() + "'");
            else if (member.getClass().equals(Admin.class))
                rset = stmt.executeQuery("select count(*) as total from sportclub.administrator WHERE username = '" + member.getUsername() + "' AND hashed_password = '" + member.getPassword() + "'");
            while(rset.next() && rset != null)
            {
                String number = rset.getString("total");
                int recurrence = Integer.parseInt(number);
                if (recurrence == 1){
                    System.out.println("Welcome back, " + member.getUsername() + "!");
                }
                if (recurrence == 0){
                    System.out.println("Bad Login. Please retry.");
                }
            }
            if (rset == null)
                throw new SQLException("Something went wrong. Class not known.");
        }
        catch(SQLException e)
        {
            System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
            System.exit(0);
        }
    }

    /**
     * Subscribe the member to an activity.
     *
     * @param activity the activity to which subscribe the member
     * @param member the member to subscribe
     */
    public static void subscribe(Activity activity, Member member){
        if(!checkSubscription(activity, member.getUsername())){
            try{
                Statement stmt = getConnection().createStatement();
                if (activity.getClass().equals(Course.class))
                    stmt.executeUpdate("insert into sportclub.activity_course(course_name, member_username) VALUES ('"+ activity.getName() + "', '" + member.getUsername() +"')");
                else if (activity.getClass().equals(Race.class))
                    stmt.executeUpdate("insert into sportclub.activity_race(race_name, member_username) VALUES ('"+ activity.getName() + "', '" + member.getUsername() +"')");}
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else System.out.println(member.getUsername() + ", you are already registered to this activity!");
    }

    /**
     * Subscribe a member to an activity.
     *
     * @param activity the activity to which subscribe the member
     * @param member the member to subscribe
     */
    public static void unsubscribe(Activity activity, Member member){
        if(checkSubscription(activity, member.getUsername())){
            try{
                Statement stmt = getConnection().createStatement();
                if (activity.getClass().equals(Course.class))
                    stmt.executeUpdate("delete from sportclub.activity_course WHERE member_username = '" + member.getUsername() + "' AND course_name = '" + activity.getName() + "'");
                else if (activity.getClass().equals(Race.class))
                    stmt.executeUpdate("delete from sportclub.activity_race WHERE member_username = '" + member.getUsername() + "' AND race_name = '" + activity.getName() + "'");;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else System.out.println("You are not registered to this activity.");
    }

    /**
     * Edit a member values inside the database.
     *
     * @param member SportClub.Member to edit
     * @param newName new Name
     * @param newSurname new Surname
     * @param newUsername new Username
     * @param newPassword new Password
     */
    public static void editMember(Member member, String newName, String newSurname, String newUsername, String newPassword){
        Member oldMember = new Member(member.getName(), member.getSurname(), member.getUsername(), member.getPassword());
        member.edit(newName, newSurname, newUsername, newPassword);
        if(checkMember(oldMember) && !checkMember(member)){
            try {
                Statement stmt = getConnection().createStatement();
                stmt.executeUpdate("update sportclub.member SET name = '" + member.getName() + "', surname = '"+ member.getSurname() + "', username = '" + member.getUsername() + "', hashed_password = '" + member.getPassword() + "' WHERE username = '" + oldMember.getUsername() + "'");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else System.out.println("Error while updating member.");
    }

    /**
     * Edit an activity's values inside the database.
     *
     * @param newName new Name
     */
    public static void editActivity(Activity activity, String newName){
        if(checkActivity(activity)){
            try {
                Statement stmt = getConnection().createStatement();
                Activity oldActivity;
                if(activity.getClass().equals(Course.class)) {
                    oldActivity = new Course(activity.getName());
                    activity.edit(newName);
                    stmt.executeUpdate("update sportclub.course SET name = '" + activity.getName() + "' WHERE name = '" + oldActivity.getName() + "'");
                }
                if(activity.getClass().equals(Race.class)) {
                    oldActivity = new Race(activity.getName());
                    activity.edit(newName);
                    stmt.executeUpdate("update sportclub.race SET name = '" + activity.getName() + "' WHERE name = '" + oldActivity.getName() + "'");
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else System.out.println("Error while updating activity. The activity to update doesn't exist.");
    }

    //Prova connessione DB
    public static void main(String[] args){
        try(Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);
            Statement stmt = conn.createStatement();)
        {
            System.out.println("OK");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
