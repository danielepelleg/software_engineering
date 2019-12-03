package it.unipr.fava_pellegrini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class TextMenu
{
    private Client client = new Client();
    CopyOnWriteArrayList<Workplace> workplaces = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Employee> employees = new CopyOnWriteArrayList<>();
    Workplace w1 = new Workplace("Barilla", "Via Mantova 166");
    Workplace w2 = new Workplace("Esselunga", "Via Emilia Ovest 230/A");
    Workplace w3 = new Workplace("Chiesi Farmaceutici S.p.A", "Via San Leonardo 96 A");

    Employee e1 = new Employee("Guido", "Barilla", "Guido", "farinaintegrale", "BRLGDU58L30G337M", w1, Mansion.Administrator, "1978-09-12", "2034-12-15");
    Employee e2 = new Employee("Pietro", "Barilla", "Peter", "pastalover", "BRLPTR98R10G337J", w1, Mansion.Official, "2019-09-16", "2052-12-31");
    Employee e3 = new Employee("Diletta", "Bighi", "Dile", "pandistelle", "DLTBGH76P46F205T", w1, Mansion.Official, "2014-03-03", "2032-06-18");
    Employee e4 = new Employee("Giacomo", "Pini", "Jack", "lemacine", "GCMPNI67S10A944S", w1, Mansion.Employee, "2011-11-03", "2030-12-03");

    Employee e5 = new Employee("Giuseppe", "Fabbri", "Beppe", "longS", "FBBGPP66A14F205P", w2, Mansion.Director, "1988-02-23", "2036-12-12");
    Employee e6 = new Employee("Silvia", "Moretti", "Silvietta", "S27ehcq0", "MRTSLV68M66L736S", w2, Mansion.Official, "1990-06-12", "2038-06-12");

    Employee e7 = new Employee("Claudio", "Marani", "Claus", "3cb49c90", "MRNCLD73D19A271V", w3, Mansion.Administrator, "2016-07-31", "2028-03-11");
    Employee e8 = new Employee("Ugo", "Di Francesco", "Frank", "pharman", "DFRGUO64B10G337G", w3, Mansion.Director, "1986-10-10", "2030-10-10");
    Employee e9 = new Employee("Gaia", "Madera", "Gaietta", "pharmawoman", "MDRGAI76S12F839D", w3, Mansion.Official, "2001-03-04", "2042-05-16");

    public void printWorkplaces(){
        int i = 0;
        for(Workplace w: workplaces){
            System.out.println(i++ + " " + w.toString());
        }
    }

    public void printEmployees(){
        int i = 0;
        for(Employee e: employees){
            System.out.println(i++ + " " + e.toString());
        }
    }

    public void createEmployees(){
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);

        employees.add(e5);
        employees.add(e6);

        employees.add(e7);
        employees.add(e8);
        employees.add(e9);
    }

    public void createWorkplaces(){
        workplaces.add(w1);
        workplaces.add(w2);
        workplaces.add(w3);
    }

    class Menu
    {

        private List<MenuItem> items = new ArrayList<MenuItem>() ;
        private String title ;

        public Menu( String title )
        {
            this.title = title ;
        }

        public Menu doOption( int option ) throws IOException, ClassNotFoundException {
            if( option == 0 ) {
                client.closeConnection();
                return null ;
            }
            option-- ;
            if( option >= items.size() )
            {
                System.out.println( "Unknown option " + option ) ;
                return this ;
            }
            items.get( option ).select() ;
            Menu next = items.get( option ).getSubMenu() ;

            return next == null ? this : next ;
        }

        public Menu addItem( MenuItem item )
        {
            items.add( item ) ;
            return this ;
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder() ;
            sb.append( title ).append( "\n" ) ;
            for( int i = 0 ; i < title.length() ; i++ )
            {
                sb.append( "-" ) ;
            }
            sb.append( "\n" ) ;
            for( int i = 0 ; i < items.size() ; i++ )
            {
                sb.append( ( i + 1 ) ).append( ") " ).append( items.get( i ) ).append( "\n" ) ;
            }
            sb.append( "0) Quit" ) ;
            return sb.toString() ;
        }
    }

    class MenuItem
    {
        private String title ;
        private Menu submenu ;
        private ActionListener onselect ;

        public String getTitle()
        {
            return title;
        }

        public MenuItem( String title, Menu submenu, ActionListener onselect )
        {
            this.title = title ;
            this.submenu = submenu ;
            this.onselect = onselect ;
        }

        public void setSubMenu( Menu submenu )
        {
            this.submenu = submenu ;
        }

        public void select()
        {
            if( onselect != null )
                onselect.actionPerformed( new ActionEvent( this, 0, "select" ) ) ;
        }

        public Menu getSubMenu()
        {
            return submenu ;
        }

        public String toString()
        {
            return title ;
        }
    }

    public Menu createMenuSystem()
    {
        MenuItem backLink = new MenuItem( "Goes back to the root menu", null, null ) ;

        Menu clientMenu = new Menu( "Client Menu" ) ;
        clientMenu.addItem( new MenuItem( "Add Employee", null, new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try {
                    System.out.println("Insert the name:");
                    Scanner sc = new Scanner(System.in);
                    String nameEmployee = sc.nextLine();
                    System.out.println("Insert the surname:");
                    String surnameEmployee = sc.nextLine();
                    System.out.println("Insert the username:");
                    String usernameEmployee = sc.nextLine();
                    System.out.println("Insert the password:");
                    String passwordEmployee = sc.nextLine();
                    System.out.println("Insert the fiscalcode:");
                    String fiscalCodeEmployee = sc.nextLine();
                    System.out.println("List of workplaces:");
                    printWorkplaces();
                    System.out.println("Select the workplace:\n\t1\n\t2\n\t3");
                    boolean chosen = false;
                    Workplace workplace = new Workplace();
                    while(!chosen){
                        String workplaceInput = sc.nextLine();
                        switch (workplaceInput) {
                            case "1" -> {
                                workplace = workplaces.get(0);
                                chosen = true;
                            }
                            case "2" -> {
                                workplace = workplaces.get(1);
                                chosen = true;
                            }
                            case "3" -> {
                                workplace = workplaces.get(2);
                                chosen = true;
                            }
                            default -> {
                                System.out.println("Insert a right value.");
                                workplaceInput = sc.nextLine();
                            }
                        }
                    }
                    System.out.println("Insert the mansion:");
                    System.out.println("Select the mansion:\n\t1 Administrator\n\t2 Director\n\t3 Official\n\t4 Employee");
                    chosen = false;
                    Mansion mansion = Mansion.Employee;
                    while(!chosen){
                        String mansionEmployee = sc.nextLine();
                        switch (mansionEmployee) {
                            case "1" -> {
                                mansion = Mansion.Administrator;
                                chosen = true;
                            }
                            case "2" -> {
                                mansion = Mansion.Director;
                                chosen = true;
                            }
                            case "3" -> {
                                mansion = Mansion.Official;
                                chosen = true;
                            }
                            case "4" -> {
                                mansion = Mansion.Employee;
                                chosen = true;
                            }
                            default -> {
                                System.out.println("Insert a right value.");
                            }
                        }
                    }
                    System.out.println("Insert the start activity date:");
                    String startActivityEmployee = sc.nextLine();
                    System.out.println("Insert the end activity date:");
                    String endActivityEmployee = sc.nextLine();
                    client.addEmployee(nameEmployee, surnameEmployee, usernameEmployee, passwordEmployee, fiscalCodeEmployee, workplace, mansion, startActivityEmployee, endActivityEmployee);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        } ) ).addItem( new MenuItem("Update Employee", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    printEmployees();
                    System.out.println("Write the username of the employee you want to update:");
                    Scanner sc = new Scanner(System.in);
                    String currentUsername = sc.nextLine();
                    System.out.println("Insert the new name:");
                    String nameEmployee = sc.nextLine();
                    System.out.println("Insert the new surname:");
                    String surnameEmployee = sc.nextLine();
                    System.out.println("Insert the new username:");
                    String usernameEmployee = sc.nextLine();
                    System.out.println("Insert the new password:");
                    String passwordEmployee = sc.nextLine();
                    System.out.println("Insert the new fiscalcode:");
                    String fiscalCodeEmployee = sc.nextLine();
                    boolean chosen = false;
                    boolean decision = false;
                    System.out.println("Do you want to use an existing Workplace? (y/n)");
                    Workplace workplace = new Workplace();
                    while(!decision){
                        String input = sc.nextLine();
                        switch(input){
                            case "y" -> {
                                decision = true;
                                printWorkplaces();
                                System.out.println("Select the new workplace:\n\t1\n\t2\n\t3");
                                while(!chosen){
                                    String workplaceInput = sc.nextLine();
                                    switch (workplaceInput) {
                                        case "1" -> {
                                            workplace = workplaces.get(0);
                                            chosen = true;
                                        }
                                        case "2" -> {
                                            workplace = workplaces.get(1);
                                            chosen = true;
                                        }
                                        case "3" -> {
                                            workplace = workplaces.get(2);
                                            chosen = true;
                                        }
                                        default -> {
                                            System.out.println("Insert a right value.");
                                        }
                                    }
                                }
                            }
                            case "n" -> {
                                decision = true;
                                System.out.println("Choose a new name for the Workplace");
                                String newName = sc.nextLine();
                                System.out.println("Choose a new address for the Workplace");
                                String newAddress = sc.nextLine();
                                workplace = new Workplace(newName, newAddress);
                            }
                            default -> {
                                System.out.println("Insert yes (y) o no (n)");
                            }
                        }
                    }
                    System.out.println("Insert the new mansion:");
                    System.out.println("Select the mansion:\n\t1 Administrator\n\t2 Director\n\t3 Official\n\t4 Employee");
                    chosen = false;
                    Mansion mansion = Mansion.Employee;
                    while(!chosen){
                        String mansionEmployee = sc.nextLine();
                        switch (mansionEmployee) {
                            case "1" -> {
                                mansion = Mansion.Administrator;
                                chosen = true;
                            }
                            case "2" -> {
                                mansion = Mansion.Director;
                                chosen = true;
                            }
                            case "3" -> {
                                mansion = Mansion.Official;
                                chosen = true;
                            }
                            case "4" -> {
                                mansion = Mansion.Employee;
                                chosen = true;
                            }
                            default -> {
                                System.out.println("Insert a right value.");
                            }
                        }
                    }
                    System.out.println("Insert the new start activity date:");
                    String startActivityEmployee = sc.nextLine();
                    System.out.println("Insert the new end activity date:");
                    String endActivityEmployee = sc.nextLine();
                    client.updateEmployee(currentUsername, nameEmployee, surnameEmployee, usernameEmployee, passwordEmployee, fiscalCodeEmployee, workplace, mansion, startActivityEmployee, endActivityEmployee);

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }) ).addItem( new MenuItem( "Research", null, new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try{
                    client.research();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        } ) ).addItem(backLink);

        Menu rootMenu = new Menu( "ROOT MENU" ) ;
        rootMenu.addItem( new MenuItem( "Login", clientMenu, new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try {
                    while (true){
                        System.out.println("Insert your username:");
                        Scanner sc = new Scanner(System.in);
                        String username = sc.nextLine();
                        System.out.println("Insert your password:");
                        String password = sc.nextLine();
                        client.login(username, password);
                        if (client.isLogged()) {
                            break;
                        }
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }));

        backLink.setSubMenu(rootMenu);

        return rootMenu ;
    }

    /** Creates a new instance of TextMenu */
    public TextMenu()
    {
        createWorkplaces();
        createEmployees();
    }

    public static void main( String[] args )
    {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
        TextMenu t = new TextMenu() ;
        Menu currentMenu = t.createMenuSystem() ;
        while( currentMenu != null )
        {
            System.out.println( currentMenu ) ;
            System.out.print( "Your Selection : " ) ;
            String inp = "" ;
            try
            {
                inp = br.readLine() ;
                currentMenu = currentMenu.doOption( Integer.parseInt( inp ) ) ;
            }
            catch( Exception ex )
            {
                System.out.println( "Didn't understand " + inp ) ;
            }
        }
    }
}
