package home.redis;

import home.entity.Subject;
import java.util.List;
import static home.constant.Constants.RUS;

public class RedisThread extends Thread{

    private RedisRepo redisQuestionRepo;
    private int lang;
    private RedisQuestionService redisQuestionService;
    private Subject subject;

    public RedisThread(int lang, Subject subject, RedisQuestionService questionService, RedisRepo redisQuestionRepo){
        this.lang = lang;
        this.subject = subject;
        this.redisQuestionService = questionService;
        this.redisQuestionRepo = redisQuestionRepo;
    }

    @Override
    public void run() {
        try{
            List<RedisQuestion> questions = redisQuestionService.makeExamQuestions(lang,subject.getSubjectId());

            if(lang == RUS){

//                List<RedisQuestion> redisQuestions = new ArrayList<>();

//                for(Question question: questions){
//                    redisQuestions.add(new RedisQuestion(question));
//                }

                redisQuestionRepo.save(subject.getRuSubject(),questions);
            }else{

//                List<RedisQuestion> redisQuestions = new ArrayList<>();
//
//                for(Question question: questions){
//                    redisQuestions.add(new RedisQuestion(question));
//                }

                redisQuestionRepo.save(subject.getSubject(),questions);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
