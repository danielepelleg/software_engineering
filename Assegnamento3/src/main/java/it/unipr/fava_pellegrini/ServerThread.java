package it.unipr.fava_pellegrini;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class ServerThread implements Runnable
{
  private static final int MAX = 100;
  private static final long SLEEPTIME = 200;

  private Server server;
  private Socket socket;

  public ServerThread(final Server s, final Socket c)
  {
    this.server = s;
    this.socket = c;
  }

  public enum RequestList {
      RequestLogin, Request;
  }
  
  @Override
  public void run()
  {
    ObjectInputStream  is = null;
    ObjectOutputStream os = null;

    try
    {
      is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
    }
    catch (Exception e)
    {
      e.printStackTrace();

      return;
    }

    String id = String.valueOf(this.hashCode());

    Random r = new Random();

    while (true)
    {
      try
      {
          // take the request sent by the client on its output stream
        Object i = is.readObject();

        if (i instanceof Request)
        {
            // convert the object in a request
          Request rq = (Request) i;

          String result = null;

            RequestList command = RequestList.valueOf(i.getClass().getSimpleName());
            switch (command){
                case RequestLogin:
                    RequestLogin requestLogin = (RequestLogin) rq;
                    result = login(requestLogin);
                    System.out.println(result);
                    break;
            }

          Thread.sleep(SLEEPTIME);

          if (os == null)
          {
            os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
          }

          Response rs = new Response(result);

          // send a response
          os.writeObject(rs);
          os.flush();

          /*
          rs = close();
            os.writeObject(rs);
            os.flush();

           */


          /*if (rs.getValue() == "quit")
          {*/
            if (this.server.getPool().getActiveCount() == 1)
            {
              this.server.close();
            }

            this.socket.close();

            return;
         // }
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }

  public String login(RequestLogin request){
      for (Employee e : this.server.getEmployees()){
          if(request.getUsername().equals(e.getUsername()) && request.getPassword().equals(e.getPassword()))
              return "Login Successful!";
      }
      return "Bad Login. Retry!";
  }

  public Response close(){
      return new Response("quit");
  }
}
