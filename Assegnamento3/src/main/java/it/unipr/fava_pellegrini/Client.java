package it.unipr.fava_pellegrini;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client
{
  private static final int SPORT = 4444;
  private static final String SHOST = "localhost";
  private static final int MAX = 100;

  private Employee user;
  private boolean logged;

  private Socket client;

  ObjectOutputStream os;
  ObjectInputStream  is;

  public void Client(){
  }

  public void setUser(Employee user) {
    this.user = user;
  }

 /* public void run()
  {
    try
    {
      // Open a socket connection
      Socket  client = new Socket(SHOST, SPORT);

      // Open Input - Output Channels
      ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
      ObjectInputStream  is = null;
/*
      while (true)
      {

        //RequestLogin rq = new RequestLogin(r.nextInt(MAX), "Gino", "1234");
        Request rq;

        System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());


        os.writeObject(rq);
        os.flush();

        if (is == null)
        {
          is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        }

        Object o = is.readObject();

        if (o instanceof Response)
        {
          Response rs = (Response) o;

          System.out.format(" and received: %s from Server%n", rs.getValue());

          /*
          if (rs.getValue() == "quit")
          {
            break;
          }
        }
      //}

      client.close();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }*/

 public void closeConnection() throws IOException, ClassNotFoundException {
     RequestCloseConnection rq = new RequestCloseConnection();
     System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());
     os.writeObject(rq);
     os.flush();
     System.out.println(this.getResponse().getValue());
 }

  public void connect(){
    try {
      // Open a socket connection
      client = new Socket(SHOST, SPORT);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public Response getResponse() throws IOException, ClassNotFoundException{
    if (is == null)
    {
      is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
    }
    Object o = is.readObject();

    if (o instanceof Response)
    {
      Response rs = (Response) o;
      System.out.format(" and received: %s from Server%n", rs.getValue());
      return rs;
      /*if (rs.getValue() == "quit")
      {
        //chiama la funzione close
      }*/
    }
    return null;
  }

  public void addEmployee(String name, String surname, String username, String password, String fiscalCode, Workplace workplace, Mansion mansion, String startActivity, String endActivity) throws IOException, ClassNotFoundException {
     if(logged){
         if(this.user.getMansion().equals(Mansion.Official)){
             RequestAddEmployee rq = new RequestAddEmployee(name,surname,username,password,fiscalCode,workplace,mansion,startActivity,endActivity);
             System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());
             os.writeObject(rq);
             os.flush();
             if(this.getResponse().getValue().equals("The employee has been added to the database!")){
                 System.out.println("The employee has been added to the database!");
             }
             else if(this.getResponse().getValue().equals("The fiscal code of this employee is already registered in the database. Please check and try again!")){
                 System.out.println("The fiscal code of this employee is already registered in the database. Please check and try again!");
             }
         }
         else System.out.println("You can't add a new Employee because you are not an Official");
     }
     else System.out.println("You are not logged into the server. You must be logged to make this operation!");
  }

  public void login(String username, String password) throws IOException, ClassNotFoundException{
    this.os = new ObjectOutputStream(client.getOutputStream());
    this.is = null;
    RequestLogin rq = new RequestLogin(username, password);
    System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());
    os.writeObject(rq);
    os.flush();
    this.checkLogin(this.getResponse());
  }

  public void checkLogin(Response response){
   if(response.getValue().equals("Bad Login. Retry!")) {
       System.out.println("You are not logged into the server. You must be logged to make researches.");
       this.logged = false;
   }
   if(response.getValue().equals("Login Successful!")) {
       this.user = (Employee) response.getObject();
       System.out.println("You are now logged as\n" + this.user.toString());
       this.logged = true;
   }
     //setUser((Employee) response.getObject());
  }

  public static void main(final String[] args)
  {
   // new Client().run();
  }
}
