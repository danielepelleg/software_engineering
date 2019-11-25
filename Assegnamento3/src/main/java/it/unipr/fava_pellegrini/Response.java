package it.unipr.fava_pellegrini;

import java.io.Serializable;

public class Response implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final String value;
  private Object object;

  public Response(final String r)
  {
    this.value = r;
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
}
