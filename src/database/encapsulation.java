package database;

public class encapsulation {
    private String mentorname;

    public String getMentorname() {
        return mentorname;
    }

    public void setMentorname(String mentorname) {
        this.mentorname = mentorname;
    }

    public encapsulation() {
        super();
    }

    public encapsulation(String mentorname) {
        super();
        this.mentorname = mentorname;
    }

    public static void main(String[] args) {
        encapsulation myObj = new encapsulation();
        myObj.setMentorname("John");
        System.out.println(myObj.getMentorname());
    }
}
