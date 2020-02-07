package Database;
import AlertBox.ErrorBox;
import AlertBox.InfoBox;
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
            PreparedStatement pstmt = null;
            pstmt = getConnection().prepareStatement("select * from sportclub.member WHERE username = ? ");
            pstmt.setString(1, member.getUsername());
            ResultSet rset = pstmt.executeQuery();
            while(rset.next())
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
            System.exit(0);
        }
        return false;
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
            PreparedStatement pstmt = null;
            if(activity.getClass().equals(Course.class))
                pstmt = getConnection().prepareStatement
                        ("select * from sportclub.activity_course " +
                                "WHERE member_username = ? AND course_name = ?");
            if(activity.getClass().equals(Race.class))
                pstmt = getConnection().prepareStatement
                        ("select * from sportclub.activity_race " +
                                "WHERE member_username = ? AND race_name = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, activity.getName());
            ResultSet rset = pstmt.executeQuery();
            while(rset.next())
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
            System.exit(0);
        }
        return false;
    }

    /**
     * Register a member in the database.
     *
     * @param member the member to register
     */
    public static void register(Member member){
        if (!checkMember(member)){
            try {
                PreparedStatement pstmt = null;
                if (member.getClass().equals(Member.class))
                    pstmt = getConnection().prepareStatement("insert into sportclub.member(name, surname, username, hashed_password, role) VALUES (?, ?, ?, ?, 'Member')");
                else if (member.getClass().equals(Admin.class))
                    pstmt = getConnection().prepareStatement("insert into sportclub.member(name, surname, username, hashed_password, role) VALUES (?, ?, ?, ?, 'Admin')");
                pstmt.setString(1, member.getName());
                pstmt.setString(2, member.getSurname());
                pstmt.setString(3, member.getUsername());
                pstmt.setString(4, member.getPassword());
                pstmt.executeUpdate();
                new InfoBox("Registration successful! Come back to login page to sign in!", "Success");
            }
            catch(SQLException e)
            {
                System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
                System.exit(0);
            }
        }
        else new ErrorBox("This user is already registered in the database!", "Registration Error");
    }

    /**
     * Sign the member or the admin in.
     *
     * @return true if the login success, false otherwise
     */
    public static boolean authenticate(Member member, boolean isAdmin) {
        try {
            PreparedStatement pstmt;
            if (!isAdmin)
                pstmt = getConnection().prepareStatement("SELECT  * FROM sportclub.member WHERE username = ? AND hashed_password = ? AND role = 'Member'");
            else
                pstmt = getConnection().prepareStatement("SELECT  * FROM sportclub.member WHERE username = ? AND hashed_password = ? AND role = 'Admin'");
            pstmt.setString(1, member.getUsername());
            pstmt.setString(2, member.getPassword());
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                String name = rset.getString("name");
                String surname = rset.getString("surname");
                String username = rset.getString("username");
                if(!isAdmin)
                    new Session(new Member(name, surname, username, ""));
                else new Session(new Admin(name, surname, username, ""));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new ErrorBox("Username or password are incorrect", "Bad Login");
        return false;
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
                stmt.executeUpdate("delete from sportclub.member WHERE username = '" + member.getUsername() + "'");
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
            }
            catch(SQLException e)
            {
                System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
                System.exit(0);
            }
        }
        else System.out.println("Error while deleting activity. This activity is not present in the database.");
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
                PreparedStatement pstmt = null;
                if (activity.getClass().equals(Course.class))
                    pstmt = getConnection().prepareStatement
                            ("insert into sportclub.activity_course(course_name, member_username) " +
                                    "VALUES (?, ?)");
                else if (activity.getClass().equals(Race.class))
                    pstmt = getConnection().prepareStatement
                            ("insert into sportclub.activity_race(race_name, member_username) " +
                                    "VALUES (?, ?)");
                pstmt.setString(1, activity.getName());
                pstmt.setString(2, member.getUsername());
                pstmt.executeUpdate();
                //new InfoBox("Subscription Successful!", "Success");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else new ErrorBox("You are already subscribed to this activity!", "Subscription Error");
    }

    /**
     * Unsubscribe a member to an activity.
     *
     * @param activity the activity to which subscribe the member
     * @param member the member to subscribe
     */
    public static void unsubscribe(Activity activity, Member member){
        if(checkSubscription(activity, member.getUsername())){
            try{
                PreparedStatement pstmt = null;
                if (activity.getClass().equals(Course.class))
                    pstmt = getConnection().prepareStatement
                            ("delete from sportclub.activity_course WHERE course_name = ? AND member_username = ?");
                else if (activity.getClass().equals(Race.class))
                    pstmt = getConnection().prepareStatement
                            ("delete from sportclub.activity_race WHERE race_name = ? AND member_username = ?");
                pstmt.setString(1, activity.getName());
                pstmt.setString(2, member.getUsername());
                pstmt.executeUpdate();
                //new InfoBox("Unsubscription Successful!", "Success");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else new ErrorBox("You are not subscribed to this activity!", "Unsubscription Error");
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

    /**
     * Return the selected Member
     *
     * @param username of the member to select
     * @return the Member
     */
    public static Member getSelectedMember(String username){
        try {
            PreparedStatement pstmt = getConnection().prepareStatement
                        ("SELECT * FROM sportclub.member WHERE member.username =?");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            Member member = new Member();
            Admin admin = new Admin();
            while (rs.next()){
                if(rs.getString(5).equals("Admin")){
                    admin.setName(rs.getString(1));
                    admin.setSurname(rs.getString(2));
                    admin.setUsername(rs.getString(3));
                    admin.setPass(rs.getString(4));
                    return admin;
                }
                else if(rs.getString(5).equals("Member")){
                    member.setName(rs.getString(1));
                    member.setSurname(rs.getString(2));
                    member.setUsername(rs.getString(3));
                    member.setPass(rs.getString(4));
                    return member;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return the selected Activity
     *
     * @param name of the activity to select
     * @return the Activity
     */
    public static Activity getSelectedActivity(String name){
        try {
            PreparedStatement pstmt = getConnection().prepareStatement("SELECT * FROM sportclub.course WHERE course.name = ?");
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            Course course = new Course("");
            while (rs.next()){
                course.setName(rs.getString(1));
            }
            pstmt = getConnection().prepareStatement("SELECT * FROM sportclub.race WHERE race.name = ?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            Race race = new Race("");
            while (rs.next()){
                race.setName(rs.getString(1));
            }
            if(!course.getName().equals("")){
                return course;
            }
            else return race;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Test database connection
     * @param args
     */
    public static void main(String[] args){
        try(Connection conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);
            Statement stmt = conn.createStatement();)
        {
            System.out.println("Database Connected!");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Database connection error!");
        }
    }
}
