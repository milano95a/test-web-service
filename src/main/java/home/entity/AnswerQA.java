package home.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by AB on 01-Aug-17.
 */

@Entity
public class AnswerQA {

    @Id
    @GeneratedValue
    @Column(name = "ANSWER_ID")
    Integer answerId;
    String answer;
    String ruanswer;

    public AnswerQA(String answer, String ruanswer) {
        this.answer = answer;
        this.ruanswer = ruanswer;
    }

    public AnswerQA() {
    }

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

    public String getRuanswer() {
        return ruanswer;
    }

    public void setRuanswer(String ruanswer) {
        this.ruanswer = ruanswer;
    }
}
