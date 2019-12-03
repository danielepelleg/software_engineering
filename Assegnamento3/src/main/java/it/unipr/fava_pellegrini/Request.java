package it.unipr.fava_pellegrini;

import java.io.Serializable;

/**
 * Request Class
 * Abstract Class representing the requests of the clients.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
class Request implements Serializable {
  private static final long serialVersionUID = 1L;
  private String string;

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

}
