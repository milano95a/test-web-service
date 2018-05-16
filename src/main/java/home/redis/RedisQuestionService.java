package home.redis;

import home.entity.Question;
import home.entity.Topic;
import home.exception.NotEnoughQuestionsException;
import home.exception.NotEnoughTopicException;
import home.exception.NotEnoughTopicsWithSufficentQuestions;
import home.repo.IQuestionRepo;
import home.repo.ITopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static home.constant.Constants.*;
import static home.constant.Constants.TOPIC_CONFIG;

@Service
public class RedisQuestionService {

    @Autowired
    IQuestionRepo questionRepo;

    @Autowired
    ITopicRepo topicRepo;

    public List<RedisQuestion> makeExamQuestions(int lang, int subjectId) throws NotEnoughQuestionsException, NotEnoughTopicException, NotEnoughTopicsWithSufficentQuestions {
//        if(subjectId == MATH || subjectId == GEOMETRY){
//            return getQuestionsByTwoSubject(lang,MATH,GEOMETRY,MATH_CONFIG);
//        }else
        if(subjectId == UZBEK || subjectId == LITERATURE){
            return getQuestionsByTwoSubject(lang,UZBEK,LITERATURE,UZBEK_CONFIG);

        }else if(subjectId == HISTORY|| subjectId == UZBEK_HISTORY){

            return getQuestionsByTwoSubject(lang,HISTORY,UZBEK_HISTORY,HISTORY_CONFIG);

        }else{
            return getQuestionsBySubject(lang,subjectId,36);
        }
    }

    private List<RedisQuestion> getQuestionsByTwoSubject(int lang, int firstSubject, int secondSubject, int firstSubjectConfig) throws NotEnoughQuestionsException, NotEnoughTopicException, NotEnoughTopicsWithSufficentQuestions{
        List<RedisQuestion> firstSubjectQuestions = getQuestionsBySubject(lang,firstSubject,firstSubjectConfig);
        List<RedisQuestion> secondSubjectQuestions = getQuestionsBySubject(lang,secondSubject,36 - firstSubjectConfig);

        List<RedisQuestion> readyQuestions = new ArrayList<>();
        readyQuestions.addAll(firstSubjectQuestions);
        readyQuestions.addAll(secondSubjectQuestions);
        return readyQuestions;
    }

    private List<RedisQuestion> getQuestionsBySubject(int lang,int subjectId, int numOfQuestion){
        List<Topic> topics = topicRepo.getBySubject_SubjectId(subjectId);
        List<Integer> topicsIds = new ArrayList<>();
        topics.forEach(topic -> {
            topicsIds.add(topic.getTopicId());
        });
        return convert(shuffleAnswers(questionRepo.getNRandomQuestions(lang,topicsIds,numOfQuestion)));
    }

    private List<RedisQuestion> getQuestionsByTopicsOfSubject(int lang, int subjectId, int numOfQuestion) throws NotEnoughQuestionsException, NotEnoughTopicException, NotEnoughTopicsWithSufficentQuestions{

        if(!isEnoughTopic(subjectId)){
            throw  new NotEnoughTopicException(subjectId);
        }

        List<Topic> randomizedTopics = getRandomizedTopicsBySubjectId(lang, subjectId);
        List<RedisQuestion> randomizedQuestions = new ArrayList<>();
        int numberOfQuestionsRemaining = 0;

        for(Topic topic: randomizedTopics){
            numberOfQuestionsRemaining = numOfQuestion - randomizedQuestions.size();
            if(numberOfQuestionsRemaining < TOPIC_CONFIG){
                randomizedQuestions.addAll(getQuestionsByTopic(lang,topic.getTopicId(),numberOfQuestionsRemaining));
            }else if(numberOfQuestionsRemaining == 0){
                return randomizedQuestions;
            }else{
                randomizedQuestions.addAll(getQuestionsByTopic(lang,topic.getTopicId(),TOPIC_CONFIG));
            }
        }

        return randomizedQuestions;
    }

    public List<RedisQuestion> getQuestionsByTopic(int lang, int topicId, int numOfQuestionAsked) throws NotEnoughQuestionsException{

        List<Question> allQuestionsByTopic = questionRepo.getQuestionsByLangAndTopic_TopicId(lang, topicId);

        if(allQuestionsByTopic.size() < numOfQuestionAsked) {
            throw new NotEnoughQuestionsException("topic",topicId);
        }

        if(allQuestionsByTopic.size() == numOfQuestionAsked) {
            return convert(allQuestionsByTopic);
        }

        Collections.shuffle(allQuestionsByTopic);
        List<Question> randomizedQuestions = allQuestionsByTopic.subList(0,numOfQuestionAsked);
        List<RedisQuestion> redisQuestions = convert(randomizedQuestions);

        for(RedisQuestion question: redisQuestions){
            Collections.shuffle(question.getAnswers());
        }
        return redisQuestions;

    }

    private List<Topic> getRandomizedTopicsBySubjectId(int lang, int subjectId) throws NotEnoughTopicsWithSufficentQuestions{

//        List<Topic> allTopics = topicRepo.getBySubject_SubjectId(subjectId);
//        Collections.shuffle(allTopics);
//        List<Topic> randomizedTopics = new ArrayList<>();
//        randomizedTopics.addAll(allTopics.subList(0,36 / TOPIC_CONFIG));
//        return randomizedTopics;
//

        List<Topic> allTopics = topicRepo.getBySubject_SubjectId(subjectId);
        Collections.shuffle(allTopics);
        List<Topic> randomizedTopics = new ArrayList<>();
        int configuredNumberOfTopics = 36 / TOPIC_CONFIG;

        for(int i = 0; i < allTopics.size(); i++){
            int currentTopicId = allTopics.get(i).getTopicId();
            int numberOfQuestionsAvailableInCurrentTopic = questionRepo.findQuestionsByTopic_TopicIdAndLang(currentTopicId,lang).size();
            if(numberOfQuestionsAvailableInCurrentTopic >= TOPIC_CONFIG){
                if(configuredNumberOfTopics > randomizedTopics.size()){
                    randomizedTopics.add(allTopics.get(i));
                }
            }
        }

        if(randomizedTopics.size() < configuredNumberOfTopics){
            throw new NotEnoughTopicsWithSufficentQuestions(subjectId);
        }
//        randomizedTopics.addAll(allTopics.subList(0,36 / TOPIC_CONFIG));
        return randomizedTopics;

    }

    private boolean isEnoughTopic(int subjectId){
        return topicRepo.findTopicsBySubject_SubjectId(subjectId).size() >= (36 / TOPIC_CONFIG);
    }

    private List<RedisQuestion> convert(List<Question> questions){

        List<RedisQuestion> redisQuestions = new ArrayList<>();

        for(Question question: questions){
            redisQuestions.add(new RedisQuestion(question));
        }

        return redisQuestions;
    }

    private List<Question> shuffleAnswers(List<Question> questions){
        questions.forEach(question -> {
            Collections.shuffle(question.getAnswers());
        });

        return questions;
    }

}
