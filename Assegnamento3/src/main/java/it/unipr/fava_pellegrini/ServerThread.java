package it.unipr.fava_pellegrini;

import java.io.*;
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
    RequestLogin, RequestAddEmployee,RequestCloseConnection;
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
              Thread.sleep(SLEEPTIME);
              if (os == null) {
                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
              }
              Response loginResponse = new Response(result);
              loginResponse.setEmployee(getEmployeeRequested((RequestLogin) rq));
              os.writeObject(loginResponse);
              os.flush();
              System.out.println(result);
              break;
            case RequestAddEmployee:
              RequestAddEmployee requestAddEmployee = (RequestAddEmployee) rq;
              result = createEmployee(requestAddEmployee);
              Thread.sleep(SLEEPTIME);
              if (os == null) {
                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
              }
              Response addEmployeeResponse = new Response(result);
              os.writeObject(addEmployeeResponse);
              os.flush();
              System.out.println(result);
              break;
            case RequestCloseConnection:
              Thread.sleep(SLEEPTIME);
              if (os == null) {
                os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
              }
              Response closeConnectionResponse = new Response("Connection Closed");
              os.writeObject(closeConnectionResponse);
              os.flush();
              this.server.close();
              return;
            default:
              this.server.close();
              return;
          }

/*
          if (this.server.getPool().getActiveCount() == 1) {
            this.server.close();
          }

          this.socket.close();
*/
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
    Employee newEmployee = new Employee(request.getName(), request.getSurname(), request.getUsername(), request.getPassword(), request.getFiscalCode(), request.getWorkplace(), request.getMansion(), request.getStartActivity(), request.getEndActivity());
    if (checkFiscalCode(newEmployee)) {
      this.server.addEmployee(newEmployee);
      return "The employee has been added to the database!";
    }
    else return "The fiscal code of this employee is already registered in the database. Please check and try again!";
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
  }

}
