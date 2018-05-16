package home.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Topic {

    @Id
    @Column(name = "TOPIC_ID")
    @GeneratedValue
    Integer topicId;

    @Column(columnDefinition="LONGTEXT")
    String topic;
    @Column(columnDefinition="LONGTEXT")
    String ruTopic;
    Integer questionConfig;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    @JsonBackReference
    Subject subject;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getQuestionConfig() {
        return questionConfig;
    }

    public void setQuestionConfig(Integer questionConfig) {
        this.questionConfig = questionConfig;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
