package it.unipr.fava_pellegrini;

import java.io.Serializable;

/**
 * Request Class
 * Abstract Class representing the requests of the clients.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
class Request implements Serializable
{
  private static final long serialVersionUID = 1L;
  private boolean logged;
  private boolean editRights;
  private boolean researchRights;

  public void setLogged(boolean rights){
    this.logged = rights;
  }

  public void setEditRights(boolean rights){
    this.editRights = rights;
  }

  public void setResearchRights(boolean rights){
    this.researchRights = rights;
  }

  public boolean isLogged(){
    return logged;
  }
  public boolean hasEditRights() {
    return editRights;
  }

  public boolean hasResearchRights() {
    return researchRights;
  }
}
