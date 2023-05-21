import java.util.ArrayList;
import java.util.List;
public abstract class Student {


    private int studentNumber;
    private String familyName;
    private String givenNames;
    private List<TopicResult> topicResults;

    public Student(int studentNumber, String familyName, String givenNames) {
        this.studentNumber = studentNumber;
        this.familyName = familyName;
        this.givenNames = givenNames;
        this.topicResults = new ArrayList<>();
    }

    public void addTopicResult(TopicResult topicResult) {
        topicResults.add(topicResult);
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public List<TopicResult> getTopicResults() {
        return topicResults;
    }

    public String getFullName() {
        return givenNames + " " + familyName;
    }

    public abstract String getDegree();
}


