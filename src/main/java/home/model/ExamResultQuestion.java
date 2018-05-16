package home.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.entity.Answer;
import home.entity.Topic;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by AB on 02-Aug-17.
 */
public class ExamResultQuestion {
    Integer questionId;
    String questionText;
    String questionImg;
    Integer lang;
    String description;
    String descriptionImg;
    List<Answer> answers;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getSelectAnswer() {
        return selectAnswer;
    }

    public void setSelectAnswer(Answer selectAnswer) {
        this.selectAnswer = selectAnswer;
    }
}
