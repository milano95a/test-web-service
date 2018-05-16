package home.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    Integer questionId;

    @Column(columnDefinition="LONGTEXT")
    String questionText;
    String questionImg;
    Integer lang;

    @Column(columnDefinition="LONGTEXT")
    String description;
    String descriptionImg;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    @JsonManagedReference
    List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "TOPIC_ID")
    Topic topic;

    @OneToOne
    @JoinColumn(name = "ANSWER_ID")
    Answer selectAnswer;


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Answer getSelectAnswer() {
        return selectAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionImg() {
        return descriptionImg;
    }

    public void setDescriptionImg(String descriptionImg) {
        this.descriptionImg = descriptionImg;
    }

    public void setSelectAnswer(Answer selectAnswer) {
        this.selectAnswer = selectAnswer;
    }
}
