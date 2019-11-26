package it.unipr.fava_pellegrini;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class ServerThread implements Runnable {
  private static final int MAX = 100;
  private static final long SLEEPTIME = 200;

  private Server server;
  private Socket socket;

  public ServerThread(final Server s, final Socket c) {
    this.server = s;
    this.socket = c;
  }

  public enum RequestList {
    RequestLogin, Request, RequestAddEmployee;
  }

  @Override
  public void run() {
    ObjectInputStream is = null;
    ObjectOutputStream os = null;

    try {
      is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
    } catch (Exception e) {
      e.printStackTrace();

      return;
    }

    String id = String.valueOf(this.hashCode());

    Random r = new Random();

    while (true) {
      try {
        // take the request sent by the client on its output stream
        Object i = is.readObject();

        if (i instanceof Request) {
          // convert the object in a request
          Request rq = (Request) i;

          String result = null;

          RequestList command = RequestList.valueOf(i.getClass().getSimpleName());
          switch (command) {
            case RequestLogin:
              RequestLogin requestLogin = (RequestLogin) rq;
              result = login(requestLogin);
              System.out.println(result);
              break;
            case RequestAddEmployee:
              RequestAddEmployee requestAddEmployee = (RequestAddEmployee) rq;
              result = createEmployee(requestAddEmployee);
              System.out.println(result);
              System.out.println(((RequestAddEmployee) rq).getName());
              break;
          }

          Thread.sleep(SLEEPTIME);
          if (os == null) {
            os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
          }
          Response rs = new Response(result);

          switch (command) {
            case RequestLogin:
              rs.setObject(getEmployeeRequested((RequestLogin) rq));
            case RequestAddEmployee:
              rs.setObject(this.server.employees.get(this.server.employees.size() - 1));
          }

          // send a response
          os.writeObject(rs);
          os.flush();


          /*if (rs.getValue() == "quit")
          {*/
          if (this.server.getPool().getActiveCount() == 1) {
            this.server.close();
          }

          this.socket.close();

          return;
          // }
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }

  public String login(RequestLogin request) {
    for (Employee e : this.server.getEmployees()) {
      if (request.getUsername().equals(e.getUsername()) && request.getPassword().equals(e.getPassword()))
        return "Login Successful!";
    }
    return "Bad Login. Retry!";
  }

  public String createEmployee(RequestAddEmployee request) {
    if (checkFiscalCode(request.getNewEmployee())) {
      this.server.addEmployee(request.getNewEmployee());
      return "The employee has been added to the database! ";
    }
    else return "The fiscal code of this employee is already registered in the database. Please check and try again! ";
    // TODO Lista codici fiscali
  }

  public boolean checkFiscalCode(Employee newEmployee) {
    for (Employee e : this.server.employees) {
      if (e.getFiscalCode().equals(newEmployee.getFiscalCode())) {
        return false;
      }
    }
    return true;
  }

  public Employee getEmployeeRequested(RequestLogin request) {
    for (Employee e : this.server.getEmployees()) {
      if (request.getUsername().equals(e.getUsername()) && request.getPassword().equals(e.getPassword()))
        return new Employee(e.getName(), e.getSurname(), e.getUsername(), e.getPassword(), e.getFiscalCode(), e.getWorkplace(), e.getMansion(), e.getStartActivity(), e.getEndActivity());
    }
    return null;
    // TODO Gestire eccezione in caso di employee null, cosa risponde il server?
  }

  public Response close() {
    return new Response("quit");
  }
}

