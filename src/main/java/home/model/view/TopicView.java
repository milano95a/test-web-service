package home.model.view;

import home.entity.Topic;

public class TopicView {

    Integer id;
    String topic;
    String ruTopic;
    String subject;
    String questionConfig;

    public TopicView(Topic topic, int uzQuestions, int rusQuestions) {
        this.id = topic.getTopicId();
        this.topic = topic.getTopic();
        this.ruTopic = topic.getRuTopic();
        this.subject = topic.getSubject().getSubject();
        this.questionConfig = uzQuestions + " / " + rusQuestions;
    }

    public TopicView(Topic topic) {
        this.id = topic.getTopicId();
        this.topic = topic.getTopic();
        this.ruTopic = topic.getRuTopic();
        this.subject = topic.getSubject().getSubject();
    }

    public TopicView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getRuTopic() {
        return ruTopic;
    }

    public void setRuTopic(String ruTopic) {
        this.ruTopic = ruTopic;
    }

    public String getQuestionConfig() {
        return questionConfig;
    }

    public void setQuestionConfig(String questionConfig) {
        this.questionConfig = questionConfig;
    }
}
