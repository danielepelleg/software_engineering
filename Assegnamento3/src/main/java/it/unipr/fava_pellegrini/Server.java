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


  public Server() throws IOException
  {
    this.socket = new ServerSocket(SPORT);
    System.out.println("Il Server è in attesa sulla porta " + this.SPORT + "...");
  }

  private void run()
  {
    this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    while (true)
    {
      try
      {
        System.out.println("In attesa di Connessione.");
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

  public static void main(final String[] args) throws IOException
  {
    new Server().run();
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
}
