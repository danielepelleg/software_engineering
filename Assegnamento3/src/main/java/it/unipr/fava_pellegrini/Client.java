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
  private boolean logged; //Tenere o no?

  private Socket client;

  ObjectOutputStream os;
  ObjectInputStream  is;

  public void setUser(Employee user) {
    this.user = user;
  }

 /* public void run()
  {
    try
    {
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }*/

 public void closeConnection() throws IOException {
   client.close();
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

  public void showResponse(Request request) throws IOException, ClassNotFoundException{

     if (is == null)
    {
      is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
    }
    Object o = is.readObject();

    if (o instanceof Response)
    {
        Response rs = (Response) o;
        System.out.format(" and received: %s from Server%n", rs.getValue());
        switch(request.getClass().getSimpleName()){
            case "RequestLogin":
                checkLogin(rs);
                break;
            case "RequestAddEmployee":
                System.out.println("The following user has been added:\n" + rs.getObject().toString());
                break;
        }

      if (rs.getValue() == "quit")
      {
        //chiama la funzione close
      }
    }
  }


  public void login(String username, String password) throws IOException, ClassNotFoundException{
      this.os = new ObjectOutputStream(client.getOutputStream());
      this.is = null;
    RequestLogin rq = new RequestLogin(username, password);
    System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());
    os.writeObject(rq);
    os.flush();
    this.showResponse(rq);
    //TODO controllare che il login sia valido
  }

  public void createUser(String name, String surname, String username, String password, String fiscalCode, Workplace workplace, Mansion mansion, String startActivity, String endActivity) throws IOException, ClassNotFoundException {
     //if(this.user != null) {
         RequestAddEmployee rq = new RequestAddEmployee(name, surname, username, password, fiscalCode, workplace, mansion, startActivity, endActivity);
         System.out.format("Client sends: %s to Server", rq.getClass().getSimpleName());
         os.writeObject(rq);
         os.flush();
         this.showResponse(rq);
         //TODO Gestire codice fiscale già presente
        // TODO Gestire utente già presente
        // TODO Aggiungere controllo utente che crea loggato (Il programma permette una sola azione per client.
        //        Se uno si logga non può fare altre azioni es. aggiungere utenti o cercare)
     //}
  }

  public void checkLogin(Response response){
   if(response.getValue().equals("Bad Login. Retry!")) {
       System.out.println("You are not logged into the server. You must be logged to make researches.");
       this.logged = false;
       // TODO Gestire nuovo tentativo
   }
   if(response.getValue().equals("Login Successful!")) {
       setUser((Employee) response.getObject());
       System.out.println("You are now logged in as\n" + this.user.toString());
       this.logged = true;
   }


  }

  public static void main(final String[] args)
  {
   // new Client().run();
  }
}

