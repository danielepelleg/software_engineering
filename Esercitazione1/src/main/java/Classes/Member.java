package Classes;

public class Member extends Person {
    public Member(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    public void Registration(Activity a){
        a.Registration(Member.this);
    }

    public void Unsubscribe(Activity a){
        a.Unsubscribe(Member.this);
    }
}