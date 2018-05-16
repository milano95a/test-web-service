package home.model;

import home.entity.Question;

import java.util.List;

/**
 * Created by AB on 02-Aug-17.
 */

public class ExamResult {
    SubjectResponse subjectTop;
    List<ExamResultQuestion> questionTop;

    SubjectResponse subjectMid;
    List<ExamResultQuestion> questionMid;

    SubjectResponse subjectBot;
    List<ExamResultQuestion> questionBot;

    public SubjectResponse getSubjectTop() {
        return subjectTop;
    }

    public void setSubjectTop(SubjectResponse subjectTop) {
        this.subjectTop = subjectTop;
    }

    public List<ExamResultQuestion> getQuestionTop() {
        return questionTop;
    }

    public void setQuestionTop(List<ExamResultQuestion> questionTop) {
        this.questionTop = questionTop;
    }

    public SubjectResponse getSubjectMid() {
        return subjectMid;
    }

    public void setSubjectMid(SubjectResponse subjectMid) {
        this.subjectMid = subjectMid;
    }

    public List<ExamResultQuestion> getQuestionMid() {
        return questionMid;
    }

    public void setQuestionMid(List<ExamResultQuestion> questionMid) {
        this.questionMid = questionMid;
    }

    public SubjectResponse getSubjectBot() {
        return subjectBot;
    }

    public void setSubjectBot(SubjectResponse subjectBot) {
        this.subjectBot = subjectBot;
    }

    public List<ExamResultQuestion> getQuestionBot() {
        return questionBot;
    }

    public void setQuestionBot(List<ExamResultQuestion> questionBot) {
        this.questionBot = questionBot;
    }
}
