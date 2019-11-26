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
  }

    public void addEmployee(String name, String surname, String username, String password, String fiscalCode, Workplace workplace, Mansion mansion, String startActivity, String endActivity) throws IOException, ClassNotFoundException {
        if(logged){
            if(this.user.getMansion().equals(Mansion.Official)){
                RequestAddEmployee rq = new RequestAddEmployee(name,surname,username,password,fiscalCode,workplace,mansion,startActivity,endActivity);
                System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());
                os.writeObject(rq);
                os.flush();
                System.out.println(this.getResponse().getValue());
            }
            else System.out.println("You can't add a new Employee because you are not an Official");
        }
        else System.out.println("You are not logged into the server. You must be logged to make this operation!");
    }

    public void research() throws IOException, ClassNotFoundException {
      if(logged){
          if(this.user.getMansion().equals(Mansion.Director) || (this.user.getMansion().equals(Mansion.Administrator))){
              RequestResearch rq = new RequestResearch(this.user.getWorkplace(), this.user.getMansion());
              System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());
              os.writeObject(rq);
              os.flush();
              System.out.println(this.getResponse().getValue());
          }
          else System.out.println("You can't make a research because you are not an Administrator or a Director");
      }
      else System.out.println("You are not logged into the server. You must be logged to make this operation!");
    }
}
