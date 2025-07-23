package studyplanner;

public class StudyTopic {
    private String subject;
    private String topic;
    private String status;

    public StudyTopic(String subject, String topic, String status) {
        this.subject = subject;
        this.topic = topic;
        this.status = status;
    }

    @Override
    public String toString() {
        return subject + " - " + topic + " (" + status + ")";
    }
}
