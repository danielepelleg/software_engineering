package it.unipr.fava_pellegrini;

import java.io.Serializable;

public class Request implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final int id;

  public Request(final int id)
  {
    this.id = id;
  }

  public int getValue()
  {
    return this.id;
  }
}
