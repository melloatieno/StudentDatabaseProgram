public class TopicResult {
    private String topicCode;
    private String grade;
    private int mark;

    public TopicResult(String topicCode, String grade) {
        this.topicCode = topicCode;
        this.grade = grade;
    }

    public TopicResult(String topicCode, String grade, int mark) {
        this.topicCode = topicCode;
        this.grade = grade;
        this.mark = mark;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public String getGrade() {
        return grade;
    }

    public int getMark() {
        return mark;
    }

    public boolean hasMark() {
        return mark != 0;
    }
}

