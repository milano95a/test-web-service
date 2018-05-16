package home.entity;

import javax.persistence.*;

/**
 * Created by AB on 01-Aug-17.
 */

@Entity
public class QuestionQA {

    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    Integer questionId;
    String questionText;
    String ruquestionText;

    @OneToOne
    @JoinColumn(name = "ANSWER_ID")
    AnswerQA answer;

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

    public AnswerQA getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerQA answer) {
        this.answer = answer;
    }

    public String getRuquestionText() {
        return ruquestionText;
    }

    public void setRuquestionText(String ruquestionText) {
        this.ruquestionText = ruquestionText;
    }


}
