import java.sql.*;

/**
 * DatabaseManager Class
 * Handles the connection with the MySQL Database.
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
     * @param username the username of the member to check
     * @return true if present, false if not present.
     */
    public static boolean checkMember(String username){
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rset = stmt.executeQuery("select count(*) as total from sportclub.member WHERE username = '" + username + "'");
            while(rset.next())
            {
                String number = rset.getString("total");
                int recurrence = Integer.parseInt(number);
                if (recurrence >= 1){
                    System.out.println("This person is already registered in the club");
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
     * Add a member into database.
     */
    public static void addMember(Person person){
        if (!checkMember(person.getUsername())){
            try {
                Statement stmt = getConnection().createStatement();
                stmt.executeUpdate("insert into sportclub.member(name, surname, username, hashed_password) VALUES ('"+ person.getName() + "', '" + person.getSurname() +"', '" + person.getUsername() + "', '"+ person.getPassword() +"')");
                System.out.println("done");
            }
            catch(SQLException e)
            {
                System.out.println("\u001B[31m\n" + e.getMessage() + "\u001B[0m");
                System.exit(0);
            }
        }
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
