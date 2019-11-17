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

  @Override
  public void run()
  {
    ObjectInputStream  is = null;
    ObjectOutputStream os = null;

    try
    {
      is = new ObjectInputStream(new BufferedInputStream(
          this.socket.getInputStream()));
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
        Object i = is.readObject();

        if (i instanceof Request)
        {
          Request rq = (Request) i;

          System.out.format("thread %s receives: %s from its client%n",
              id, rq.getValue());
          Thread.sleep(SLEEPTIME);

          if (os == null)
          {
            os = new ObjectOutputStream(new BufferedOutputStream(
                this.socket.getOutputStream()));
          }

          Response rs = new Response(r.nextInt(MAX));

          System.out.format("thread %s sends: %s to its client%n",
              id, rs.getValue());
          os.writeObject(rs);
          os.flush();

          if (rs.getValue() == 0)
          {
            if (this.server.getPool().getActiveCount() == 1)
            {
              this.server.close();
            }

            this.socket.close();

            return;
          }
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }
}
