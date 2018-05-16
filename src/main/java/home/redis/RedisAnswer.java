package home.redis;

import com.fasterxml.jackson.annotation.JsonBackReference;
import home.entity.Answer;
import home.entity.Question;

import javax.persistence.*;

public class RedisAnswer {

    Integer answerId;
    String answer;
    String answerImg;
    Boolean status;

    public RedisAnswer(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.answer = answer.getAnswer();
        this.answerImg = answer.getAnswerImg();
        this.status = answer.getStatus();
    }

    public RedisAnswer(Integer answerId, String answer, String answerImg, Boolean status) {
        this.answerId = answerId;
        this.answer = answer;
        this.answerImg = answerImg;
        this.status = status;
    }

    public RedisAnswer() {
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

    public String getAnswerImg() {
        return answerImg;
    }

    public void setAnswerImg(String answerImg) {
        this.answerImg = answerImg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
