package home.utils;

import home.entity.Question;
import home.entity.Subject;
import home.exception.NotEnoughQuestionsException;
import home.exception.NotEnoughTopicException;
import home.exception.NotEnoughTopicsWithSufficentQuestions;
import home.redis.RedisQuestionService;
import home.redis.RedisRepo;
import home.redis.RedisThread;
import home.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static home.constant.Constants.RUS;

@Component
public class ExamCache {

    @Autowired
    private RedisRepo redisQuestionRepo;

    @Autowired
    private RedisQuestionService redisQuestionService;

    @Autowired
    private QuestionService questionService;



    public List<Question> getExamQuestionsFromCache(int lang, Subject subject) throws NotEnoughQuestionsException, NotEnoughTopicException,NotEnoughTopicsWithSufficentQuestions{
        if(lang == RUS){
            if(redisQuestionRepo.isKeyExist(subject.getRuSubject())){
                loadToCache(lang,subject,redisQuestionService,redisQuestionRepo);
                return (List<Question>) redisQuestionRepo.findByKey(subject.getRuSubject());
            }else{
                loadToCache(lang,subject,redisQuestionService,redisQuestionRepo);
                return questionService.makeExamQuestions(lang,subject.getSubjectId());
            }
        }else{
            if(redisQuestionRepo.isKeyExist(subject.getSubject())){
                loadToCache(lang,subject,redisQuestionService,redisQuestionRepo);
                return (List<Question>) redisQuestionRepo.findByKey(subject.getSubject());
            }else{
                loadToCache(lang,subject,redisQuestionService,redisQuestionRepo);
                return questionService.makeExamQuestions(lang,subject.getSubjectId());
            }
        }
    }

    private void loadToCache(int lang, Subject subject, RedisQuestionService questionService, RedisRepo redisQuestionRepo){
        RedisThread redisThread = new RedisThread(lang,subject,questionService,redisQuestionRepo);
        redisThread.start();
    }
}
