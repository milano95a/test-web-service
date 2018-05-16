package home.redis;


import com.fasterxml.jackson.annotation.JsonBackReference;
import home.entity.Subject;
import home.entity.Topic;

import javax.persistence.*;

public class RedisTopic {

    Integer topicId;
    String topic;
    String ruTopic;
    Integer questionConfig;

    public RedisTopic() {
    }

    public RedisTopic(Topic topic) {
        this.topicId = topic.getTopicId();
        this.topic = topic.getTopic();
        this.ruTopic = topic.getRuTopic();
        this.questionConfig = topic.getQuestionConfig();
    }

    public RedisTopic(Integer topicId, String topic, String ruTopic, Integer questionConfig) {
        this.topicId = topicId;
        this.topic = topic;
        this.ruTopic = ruTopic;
        this.questionConfig = questionConfig;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
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

    public Integer getQuestionConfig() {
        return questionConfig;
    }

    public void setQuestionConfig(Integer questionConfig) {
        this.questionConfig = questionConfig;
    }
}
