package it.unipr.fava_pellegrini;

public class RequestResearch extends Request {
    Workplace workplace;
    Mansion mansion;

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
