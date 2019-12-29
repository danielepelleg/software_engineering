package SportClub;
import java.util.ArrayList;

/**
 * Session Class
 * Keep trace of the session login.
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public class Session {

    public ArrayList<Member> sessions = new ArrayList<>();
    public static Member currentMember;

    /**
     * Constructor Class
     * Update the session of the current member and add him to sessions list.
     *
     * @param member the member logged
     */
    public Session(Member member){
        currentMember = member;
        sessions.add(currentMember);
    }

    /**
     * Get the current session.
     *
     * @return the current member logged.
     */
    public static Member getCurrentSession(){
        return currentMember;
    }
}
