package it.unipr.fava_pellegrini;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server
{
  private static final int COREPOOL = 5;
  private static final int MAXPOOL = 100;
  private static final long IDLETIME = 5000;
  ArrayList<Workplace> workplaces;
  ArrayList<Employee> employees;

  private static final int SPORT = 4444;

  private ServerSocket socket;
  private ThreadPoolExecutor pool;


  public Server() {
    try {
      this.socket = new ServerSocket(SPORT);
      System.out.println("Il Server è in attesa sulla porta " + this.SPORT + "...");
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  public Server(ArrayList<Employee> employees, ArrayList<Workplace> workplaces){
    try {
      this.socket = new ServerSocket(SPORT);
      System.out.println("Il Server è in attesa sulla porta " + this.SPORT + "...");
      this.employees = employees;
      this.workplaces = workplaces;
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  private void run()
  {
    this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    while (true)
    {
      try
      {
        System.out.println("In attesa di Connessione.");
        //creating socket and waiting for client connection
        Socket s = this.socket.accept();
        System.out.println("Connessione accettata da: "+ s.getInetAddress());
        this.pool.execute(new ServerThread(this, s));
      }
      catch (Exception e)
      {
        break;
      }
    }

    this.pool.shutdown();
  }

  public ThreadPoolExecutor getPool()
  {
    return this.pool;
  }

  public void close()
  {
    try
    {
      this.socket.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public ArrayList<Workplace> getWorkplaces() {
    return workplaces;
  }

  public void setWorkplaces(ArrayList<Workplace> workplaces) {
    this.workplaces = workplaces;
  }

  public ArrayList<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(ArrayList<Employee> employees) {
    this.employees = employees;
  }

  public void addEmployee(Employee newEmployee){
    this.employees.add(newEmployee);
  }

  public void addWorkplace(Workplace newWorkplace){
    this.workplaces.add(newWorkplace);
  }

  public static void main(final String[] args) throws IOException
  {
    ArrayList<Workplace> workplaces = new ArrayList<Workplace>();
    Workplace w1 = new Workplace("Barilla", "Via Mantova 166");
    Workplace w2 = new Workplace("Esselunga", "Via Emilia Ovest 230/A");
    Workplace w3 = new Workplace("Chiesi Farmaceutici S.p.A", "Via San Leonardo 96 A");
    workplaces.add(w1);
    workplaces.add(w2);
    workplaces.add(w3);

    Employee e1 = new Employee("Guido", "Barilla", "Guido", "farinaintegrale", "BRLGDU58L30G337M", w1, Mansion.Administrator, "1978-09-12", "2034-12-15");
    Employee e2 = new Employee("Pietro", "Barilla", "Peter", "pastalover", "BRLPTR98R10G337J", w1, Mansion.Director, "2019-09-16", "2052-12-31");
    Employee e3 = new Employee("Diletta", "Bighi", "Dile", "pandistelle", "DLTBGH76P46F205T", w1, Mansion.Official, "2014-03-03", "2032-06-18");
    Employee e4 = new Employee("Giacomo", "Pini", "Jack", "lemacine", "GCMPNI67S10A944S", w1, Mansion.Employee, "2011-11-03", "2030-12-03");

    Employee e5 = new Employee("Giuseppe", "Fabbri", "Beppe", "longS", "FBBGPP66A14F205P", w2, Mansion.Director, "1988-02-23", "2036-12-12");
    Employee e6 = new Employee("Silvia", "Moretti", "Silvietta", "S27ehcq0", "MRTSLV68M66L736S", w2, Mansion.Official, "1990-06-12", "2038-06-12");

    Employee e7 = new Employee("Claudio", "Marani", "Claus", "3cb49c90", "MRNCLD73D19A271V", w3, Mansion.Administrator, "2016-07-31", "2028-0-11");
    Employee e8 = new Employee("Ugo", "Di Francesco", "Frank", "pharman", "DFRGUO64B10G337G", w3, Mansion.Director, "1986-10-10", "2030-10-10");
    Employee e9 = new Employee("Gaia", "Madera", "Gaietta", "pharmawoman", "MDRGAI76S12F839D", w3, Mansion.Official, "2001-03-04", "2042-05-16");


    ArrayList<Employee> employees = new ArrayList<>();
    employees.add(e1);
    employees.add(e2);
    employees.add(e3);
    employees.add(e4);

    employees.add(e5);
    employees.add(e6);

    employees.add(e7);
    employees.add(e8);
    employees.add(e9);

    new Server(employees, workplaces).run();
  }
}
