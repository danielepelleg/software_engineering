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
  private Socket client;


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

  public void showResponse() throws IOException, ClassNotFoundException {
    ObjectInputStream  is = null;

    if (is == null)
    {
      is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
    }

    Object o = is.readObject();

    if (o instanceof Response)
    {
      Response rs = (Response) o;

      System.out.format(" and received: %s from Server%n", rs.getValue());

      if (rs.getValue() == "quit")
      {
        //chiama la funzione close
      }
    }
  }


  public void login(String username, String password) throws IOException, ClassNotFoundException {
    ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
    RequestLogin req = new RequestLogin(username, password);
    os.writeObject(req);
    os.flush();
    this.showResponse();
    //TODO controllare che il login sia valido
    //TODO Assegnare i valori dell'utente loggato a questo client
  }

  public static void main(final String[] args)
  {
   // new Client().run();
  }
}

