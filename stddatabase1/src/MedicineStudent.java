import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineStudent extends Student {

    private List<String> prizes;
    private Map<String, List<Integer>> topicMarks;

    public MedicineStudent(int studentNumber, String familyName, String givenNames, List<String> prizes) {
        super(studentNumber, familyName, givenNames);
        this.prizes = prizes;
        this.topicMarks = new HashMap<>();
    }

    public List<String> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<String> prizes) {
        this.prizes = prizes;
    }
    public void calculatePrize(Prize prize) {
        List<Integer> matchingTopics = getMatchingTopics(prize.getTopicPattern());

        if (matchingTopics.size() >= prize.getMinTopics()) {
            double highestAverageMark = -1;
            double totalMark = 0;

            for (int topic : matchingTopics) {
                if (topicMarks.containsKey(topic)) {
                    List<Integer> marks = topicMarks.get(topic);
                    for (int mark : marks) {
                        totalMark += mark;
                    }
                }
            }

            double averageMark = totalMark / matchingTopics.size();

            if (averageMark > highestAverageMark) {
                highestAverageMark = averageMark;
                prizes.add(prize.getPrizeName());
            }
        }
    }

    private List<Integer> getMatchingTopics(String topicPattern) {
        List<Integer> matchingTopics = new ArrayList<>();

        for (String topicCode : topicMarks.keySet()) {
            if (topicCode.startsWith(topicPattern)) {
                matchingTopics.add(Integer.parseInt(topicCode.substring(4)));
            }
        }

        return matchingTopics;
    }

    @Override
    public void addTopicResult(TopicResult topicResult) {
        super.addTopicResult(topicResult);

        String topicCode = topicResult.getTopicCode();
        int mark = topicResult.getMark();

        if (topicMarks.containsKey(topicCode)) {
            topicMarks.get(topicCode).add(mark);
        } else {
            List<Integer> marks = new ArrayList<>();
            marks.add(mark);
            topicMarks.put(topicCode, marks);
        }
    }

    @Override
    public String getDegree() {
        return "Medicine";
    }
}
