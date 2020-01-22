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

    public static ArrayList<Member> membersSessions = new ArrayList<>();
    public static ArrayList<Admin> adminSessions = new ArrayList<>();
    public static Member currentMember;
    public static Admin currentAdmin;

    /**
     * Constructor Class
     * Update the session of the current member and add him to sessions list.
     *
     * @param member the member logged
     */
    public Session(Member member){
        currentMember = member;
        membersSessions.add(currentMember);
    }

    /**
     * Constructor Class
     * Update the session of the current admin and add him to sessions list.
     * @param admin
     */
    public Session(Admin admin){
        currentAdmin = admin;
        adminSessions.add(currentAdmin);
    }

    /**
     * Get the current session of the Members.
     *
     * @return the current member logged (last one inserted in the session list).
     */
    public static Member getMemberSession(){
        return membersSessions.get(membersSessions.size()-1);
    }

    /**
     * Get the current session of the Members.
     *
     * @return the current member logged (last one inserted in the session list).
     */
    public static Member getAdminSession(){
        return adminSessions.get(adminSessions.size()-1);
    }

}
