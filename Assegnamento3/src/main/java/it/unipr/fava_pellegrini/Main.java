package it.unipr.fava_pellegrini;

/**
 * Main Class
 * Test, through a simulation with a MENU, the classes and methods created
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main
{
    private Client client = new Client();
    private ArrayList<Workplace> workplaces;
    private ArrayList<Employee> employees;
    private Scanner sc = new Scanner(System.in);
    private boolean chosen = false;

    private void printWorkplaces() throws IOException, ClassNotFoundException {
        int i = 1;
        workplaces = client.getObjects("Workplaces");
        for(Workplace w: workplaces){
            System.out.println(i++ + ") " + w.toString());
        }
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
                    if(!client.getUser().getMansion().equals(Mansion.Employee)){
                        System.out.println("Insert the name:");
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
                        System.out.println("Select the workplace:\n\t1 For the first\n\t2 For the second\n\t3 For the third");
                        Workplace workplace = chooseWorkplace();
                        System.out.println("Insert the mansion:");
                        System.out.println("Select the mansion:\n\t1 Administrator\n\t2 Director\n\t3 Official\n\t4 Employee");
                        Mansion mansion = chooseMansion();
                        System.out.println("Insert the start activity date:");
                        String startActivityEmployee = sc.nextLine();
                        System.out.println("Insert the end activity date:");
                        String endActivityEmployee = sc.nextLine();
                        client.addEmployee(nameEmployee, surnameEmployee, usernameEmployee, passwordEmployee, fiscalCodeEmployee, workplace, mansion, startActivityEmployee, endActivityEmployee);
                    } else System.out.println("You don't have permission to do this operation!\n");

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        } ) ).addItem( new MenuItem("Update Employee", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (!client.getUser().getMansion().equals(Mansion.Employee)){
                        client.printEmployees("Employees");
                        System.out.println("Write the current username of the employee you want to update:");
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
                        boolean decision = false;
                        System.out.println("Do you want to use an existing Workplace? (y/n)");
                        Workplace workplace = new Workplace();
                        while(!decision){
                            String input = sc.nextLine();
                            switch(input){
                                case "y" -> {
                                    decision = true;
                                    printWorkplaces();
                                    System.out.println("Select the workplace:\n\t1 For the first\n\t2 For the second\n\t3 For the third");
                                    workplace = chooseWorkplace();
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
                        Mansion mansion = chooseMansion();
                        System.out.println("Insert the new start activity date:");
                        String startActivityEmployee = sc.nextLine();
                        System.out.println("Insert the new end activity date:");
                        String endActivityEmployee = sc.nextLine();
                        client.updateEmployee(currentUsername, nameEmployee, surnameEmployee, usernameEmployee, passwordEmployee, fiscalCodeEmployee, workplace, mansion, startActivityEmployee, endActivityEmployee);
                    } else System.out.println("You don't have permission to do this operation!\n");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }) ).addItem( new MenuItem( "Research", null, new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try{
                    if (!client.getUser().getMansion().equals(Mansion.Employee)){
                        client.research();
                    } else System.out.println("You don't have permission to do this operation!\n");
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

    public Workplace chooseWorkplace() throws IOException, ClassNotFoundException {
        workplaces = client.getObjects("Workplaces");
        chosen = false;
        while(!chosen){
            String workplaceInput = sc.nextLine();
            switch (workplaceInput) {
                case "1" -> {
                    chosen = true;
                    return workplaces.get(0);
                }
                case "2" -> {
                    chosen = true;
                    return workplaces.get(1);
                }
                case "3" -> {
                    chosen = true;
                    return workplaces.get(2);
                }
                default -> {
                    System.out.println("Insert a right value.");
                }
            }
        }
        return null;
    }

    public Mansion chooseMansion(){
        chosen = false;
        while(!chosen){
            String mansionEmployee = sc.nextLine();
            switch (mansionEmployee) {
                case "1" -> {
                    chosen = true;
                    return Mansion.Administrator;
                }
                case "2" -> {
                    chosen = true;
                    return Mansion.Director;
                }
                case "3" -> {
                    chosen = true;
                    return Mansion.Official;
                }
                case "4" -> {
                    chosen = true;
                    return Mansion.Employee;
                }
                default -> {
                    System.out.println("Insert a right value.");
                }
            }
        }
        return null;
    }

    /** Creates a new instance of TextMenu */
    public Main() {

    }

    public static void main( String[] args )
    {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
        Main t = new Main() ;
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
