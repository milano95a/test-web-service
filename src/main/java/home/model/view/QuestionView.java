package home.model.view;

import home.entity.Question;
import home.entity.Subject;
import home.entity.Topic;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import static home.constant.Constants.*;

public @Data class QuestionView {

    private Integer id;

    private String subject;
    private String language;
    private String questionText;
    private MultipartFile questionImg;
    private String questionImgPath;
    private String descText;
    private MultipartFile descImg;
    private String descImgPath;
    private String aText;
    private MultipartFile aImg;
    private String aImgPath;
    private String bText;
    private MultipartFile bImg;
    private String bImgPath;
    private String cText;
    private MultipartFile cImg;
    private String cImgPath;
    private String dText;
    private MultipartFile dImg;
    private String dImgPath;
    private String correct;
    private String topic;

    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;

    private boolean isQuestionImgUpdated;
    private boolean isAImgUpdated;
    private boolean isBImgUpdated;
    private boolean isCImgUpdated;
    private boolean isDImgUpdated;
    private boolean isDescriptionImgUpdated;


    public QuestionView(Question question, Subject subject, Topic topic) {
        this.id = question.getQuestionId();
        this.subject = subject.getSubject();
        this.topic = topic.getTopic();
        this.descText = question.getDescription();
        this.questionText = question.getQuestionText();

        if (question.getAnswers().size() > 0) {
            if (question.getAnswers().get(0).getStatus()) {
                this.a = true;
            }

            if(question.getAnswers().get(0).getAnswer() != null){
                this.aText = question.getAnswers().get(0).getAnswer();
            }

            if(question.getAnswers().get(0).getAnswerImg() != null){
                this.aImgPath = IMAGE_DOMAIN + question.getAnswers().get(0).getAnswerImg();
            }
        }

        if (question.getAnswers().size() > 1) {

            if (question.getAnswers().get(1).getStatus()) {
                this.b = true;
            }

            if(question.getAnswers().get(1).getAnswer() != null){
                this.bText = question.getAnswers().get(1).getAnswer();
            }

            if(question.getAnswers().get(1).getAnswerImg() != null){
                this.bImgPath = IMAGE_DOMAIN + question.getAnswers().get(1).getAnswerImg();
            }
        }

        if (question.getAnswers().size() > 2) {

            if (question.getAnswers().get(2).getStatus()) {
                this.c = true;
            }
            if(question.getAnswers().get(2).getAnswer() != null){
                this.cText = question.getAnswers().get(2).getAnswer();
            }

            if(question.getAnswers().get(2).getAnswerImg() != null){
                this.cImgPath = IMAGE_DOMAIN + question.getAnswers().get(2).getAnswerImg();
            }
        }

        if (question.getAnswers().size() > 3) {
            if (question.getAnswers().get(3).getStatus()) {
                this.d = true;
            }
            if(question.getAnswers().get(3).getAnswer() != null){
                this.dText = question.getAnswers().get(3).getAnswer();
            }
            if(question.getAnswers().get(3).getAnswerImg() != null){
                this.dImgPath = IMAGE_DOMAIN + question.getAnswers().get(3).getAnswerImg();
            }
        }

        if(question.getLang() == RUS){
            this.language = RUS_STRING;
        }else{
            this.language = UZ_STRING;
        }

        if(question.getQuestionImg() != null){
            this.questionImgPath = IMAGE_DOMAIN + question.getQuestionImg();
        }

        if(question.getDescriptionImg() != null){
            this.descImgPath = IMAGE_DOMAIN + question.getDescriptionImg();
        }
    }

    public QuestionView() {}

    public boolean getIsQuestionImgUpdated() {
        return isQuestionImgUpdated;
    }

    public void setIsQuestionImgUpdated(boolean isQuestionImgUpdated) {
        this.isQuestionImgUpdated = isQuestionImgUpdated;
    }

    public boolean getIsAImgUpdated() {
        return isAImgUpdated;
    }

    public void setIsAImgUpdated(boolean isAImgUpdated) {
        this.isAImgUpdated = isAImgUpdated;
    }

    public boolean getIsBImgUpdated() {
        return isBImgUpdated;
    }

    public void setIsBImgUpdated(boolean isBImgUpdated) {
        this.isBImgUpdated = isBImgUpdated;
    }

    public boolean getIsCImgUpdated() {
        return isCImgUpdated;
    }

    public void setIsCImgUpdated(boolean isCImgUpdated) {
        this.isCImgUpdated = isCImgUpdated;
    }

    public boolean getIsDImgUpdated() {
        return isDImgUpdated;
    }

    public void setIsDImgUpdated(boolean isDImgUpdated) {
        this.isDImgUpdated = isDImgUpdated;
    }

    public boolean getIsDescriptionImgUpdated() {
        return isDescriptionImgUpdated;
    }

    public void setIsDescriptionImgUpdated(boolean isDescriptionImgUpdated) {
        this.isDescriptionImgUpdated = isDescriptionImgUpdated;
    }
}