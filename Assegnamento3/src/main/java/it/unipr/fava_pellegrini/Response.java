package it.unipr.fava_pellegrini;

import java.io.Serializable;

public class Response implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final String value;

  public Response(final String r)
  {
    this.value = r;
  }

  public String getValue()
  {
    return this.value;
  }
}
