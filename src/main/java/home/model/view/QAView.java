package home.model.view;

import home.entity.QuestionQA;

/**
 * Created by AB on 19-Aug-17.
 */
public class QAView {

    Integer id;
    String questionText;
    String ruquestionText;
    String answer;
    String ruanswer;

    public QAView(QuestionQA questionQA) {
        this.id = questionQA.getQuestionId();
        this.questionText = questionQA.getQuestionText();
        this.ruquestionText = questionQA.getRuquestionText();
        this.answer = questionQA.getAnswer().getAnswer();
        this.ruanswer = questionQA.getAnswer().getRuanswer();
    }

    public QAView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getRuquestionText() {
        return ruquestionText;
    }

    public void setRuquestionText(String ruquestionText) {
        this.ruquestionText = ruquestionText;
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
