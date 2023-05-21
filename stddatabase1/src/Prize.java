public class Prize {
    private String prizeName;
    private String topicPattern;
    private int minTopics;

    public Prize(String prizeName, String topicPattern, int minTopics) {
        this.prizeName = prizeName;
        this.topicPattern = topicPattern;
        this.minTopics = minTopics;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public String getTopicPattern() {
        return topicPattern;
    }

    public int getMinTopics() {
        return minTopics;
    }
}

