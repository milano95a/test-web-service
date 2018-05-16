package home.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    @Column(name = "ANSWER_ID")
    Integer answerId;

    @Column(columnDefinition="LONGTEXT")
    String answer;
    String answerImg;
    Boolean status;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    @JsonBackReference
    Question question;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerImg() {
        return answerImg;
    }

    public void setAnswerImg(String answerImg) {
        this.answerImg = answerImg;
    }
}
