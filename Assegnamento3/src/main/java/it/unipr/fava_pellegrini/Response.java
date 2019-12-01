package it.unipr.fava_pellegrini;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Response CLass
 * A response is sent by the server after it receives a request. It has a String which contains the status of the request,
 * if an error occurred or the request is been elaborated, and a String message containing what the client has requested.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Response implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final String value;
  private String message;
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

  /**
   * Set an employee in the Object attribute
   *
   * @param employee the employee to be set
   */
  public void setEmployee(Employee employee){
    this.object = employee;
  }

  /**
   * Set a list of employees in the Object attribute
   *
   * @param employees the list to be set
   */
  public void setList(ArrayList<Employee> employees) { this.object = employees; }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message){
    this.message = message;
  }

  /**
   * Calculate the size of the list casted in the Object attribute
   *
   * @return the size of the list
   */
  public int getListSize(){
    ArrayList<Employee> employees = (ArrayList<Employee>) this.object;
    return employees.size();
  }

  /**
   * Set the content of the list in the Object attribute and its size as the message attribute
   */
  public void setMessageListed(){
    String result = "\nHere's the list of the employee:\n" +
            ListToString() + "Total: " + getListSize() + "\n";
    this.message = result;
  }

  /**
   * Convert the list in the Object attribute in a String
   *
   * @return
   */
  public String ListToString(){
    String result = "";
    ArrayList<Employee> employees = (ArrayList<Employee>) this.object;
    for(Employee e : employees){
      result += e.toString() + "\n";
    }
    return result;
  }
}
