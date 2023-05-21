public class ScienceStudent extends Student{
    public ScienceStudent(int studentNumber, String familyName, String givenNames) {
        super(studentNumber, familyName, givenNames);
    }

    @Override
    public String getDegree() {
        return "Science";
    }
}


