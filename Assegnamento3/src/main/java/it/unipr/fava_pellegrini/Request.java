package it.unipr.fava_pellegrini;

import java.io.Serializable;

public class Request implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final int value;

  public Request(final int v)
  {
    this.value = v;
  }

  public int getValue()
  {
    return this.value;
  }
}
