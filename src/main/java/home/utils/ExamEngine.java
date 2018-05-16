package home.utils;

import home.entity.Question;
import home.entity.Subject;
import home.exception.NotEnoughQuestionsException;
import home.exception.NotEnoughTopicException;
import home.exception.NotEnoughTopicsWithSufficentQuestions;
import home.exception.QuestionConfigurationException;
import home.model.Exam;
import home.model.SubjectResponse;
import home.repo.ISubjectRepo;
import home.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static home.constant.Constants.CACHING;
import static home.constant.Constants.CHEMISTRY;

/**
 * Created by AB on 07-Sep-17.
 */

@Component
public class ExamEngine {

    @Autowired
    ISubjectRepo subjectRepo;

    @Autowired
    QuestionService questionService;

    @Autowired
    ExamCache examCache;

    public Exam makeExam(int lang, int subjectTop, int subjectMid, int subjectBot) throws QuestionConfigurationException, NotEnoughQuestionsException, NotEnoughTopicException, NotEnoughTopicsWithSufficentQuestions {

        List<Question> questionsTop = new ArrayList<>();
        List<Question> questionsMid = new ArrayList<>();
        List<Question> questionsBot = new ArrayList<>();

        Subject sTop = subjectRepo.findOne(subjectTop);
        Subject sMid = subjectRepo.findOne(subjectMid);
        Subject sBot = subjectRepo.findOne(subjectBot);

        SubjectResponse srTop = new SubjectResponse(sTop,lang);
        SubjectResponse srMid = new SubjectResponse(sMid,lang);
        SubjectResponse srBot = new SubjectResponse(sBot,lang);



        if(CACHING){
            questionsTop.addAll(examCache.getExamQuestionsFromCache(lang,sTop));
            questionsMid.addAll(examCache.getExamQuestionsFromCache(lang,sMid));
            questionsBot.addAll(examCache.getExamQuestionsFromCache(lang,sBot));
        }else{
            questionsTop.addAll(questionService.makeExamQuestions(lang,subjectTop));
            questionsMid.addAll(questionService.makeExamQuestions(lang,subjectMid));
            questionsBot.addAll(questionService.makeExamQuestions(lang,subjectBot));
        }

        Exam exam = new Exam();
        exam.setQuestionTop(questionsTop);
        exam.setQuestionMid(questionsMid);
        exam.setQuestionBot(questionsBot);
        exam.setSubjectTop(srTop);
        exam.setSubjectMid(srMid);
        exam.setSubjectBot(srBot);

        return exam;
    }

}
