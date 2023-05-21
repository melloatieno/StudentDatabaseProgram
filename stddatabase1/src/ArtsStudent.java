public class ArtsStudent extends Student{

    private String major;
    private String minor;

    public ArtsStudent(int studentNumber, String familyName, String givenNames, String major, String minor) {
        super(studentNumber, familyName, givenNames);
        this.major = major;
        this.minor = minor;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    @Override
    public String getDegree() {
        return "Arts";
    }
}


