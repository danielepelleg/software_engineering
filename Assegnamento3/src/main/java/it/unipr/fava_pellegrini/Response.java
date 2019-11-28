package it.unipr.fava_pellegrini;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Response CLass
 * A response is sent by the server after it receives a request. It has a String which contains the status of the request,
 * if an error occurred or the request is been elaborated, and a String message containing what the client has requested.
 */
public class Response implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final String value;
  private final String message;
  private Object object;

  /**
   * Class Constructor
   *
   * @param r the value status of the response
   * @param m the message of the response
   */
  public Response(final String r, String m)
  {
    this.value = r;
    this.message = m;
  }

  public String getValue()
  {
    return this.value;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public Object getObject(){
    return this.object;
  }

  public void setEmployee(Employee employee){
    this.object = employee;
  }

  public void setList(ArrayList<Employee> employees) { this.object = employees; }

  public String getMessage() {
    return message;
  }
}
