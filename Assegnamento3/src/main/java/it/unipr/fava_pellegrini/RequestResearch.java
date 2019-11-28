package it.unipr.fava_pellegrini;

/**
 * RequestResearch Class - Request Subclass
 * Each RequestResearch has the Workplace of which the client wants to research the employees and the mansion
 * of the employee logged who wants to perform the action. The client can do the research just if the user logged is a Director or an Administrator.
 * Researches won't show the Administrators if a Director does the research, but they will show them if the action is performed by an Administrator-
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class RequestResearch extends Request {
    Workplace workplace;
    Mansion mansion;

    /**
     * Class Constructor
     *
     * @param workplace the workplace to set the research in
     * @param mansion the mansion of the user logged
     */
    public RequestResearch(Workplace workplace, Mansion mansion){
        this.workplace = workplace;
        this.mansion = mansion;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Mansion getMansion() {
        return mansion;
    }

    public void setMansion(Mansion mansion) {
        this.mansion = mansion;
    }
}
