package home.service;

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

@Service
public class QuestionService implements BaseService{

    IQuestionRepo questionRepo;
    ITopicRepo topicRepo;

    @Autowired
    public QuestionService(IQuestionRepo questionRepo, ITopicRepo topicRepo){
        this.questionRepo = questionRepo;
        this.topicRepo = topicRepo;
    }

    public List<Question> makeExamQuestions(int lang, int subjectId) throws NotEnoughQuestionsException, NotEnoughTopicException, NotEnoughTopicsWithSufficentQuestions{
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

    private List<Question> getQuestionsByTwoSubject(int lang, int firstSubject, int secondSubject, int firstSubjectConfig) throws NotEnoughQuestionsException, NotEnoughTopicException, NotEnoughTopicsWithSufficentQuestions{
        List<Question> firstSubjectQuestions = getQuestionsBySubject(lang,firstSubject,firstSubjectConfig);
        List<Question> secondSubjectQuestions = getQuestionsBySubject(lang,secondSubject,36 - firstSubjectConfig);

        List<Question> readyQuestions = new ArrayList<>();
        readyQuestions.addAll(firstSubjectQuestions);
        readyQuestions.addAll(secondSubjectQuestions);
        return readyQuestions;
    }

    private List<Question> getQuestionsBySubject(int lang,int subjectId, int numOfQuestion){
        List<Topic> topics = topicRepo.getBySubject_SubjectId(subjectId);
        List<Integer> topicsIds = new ArrayList<>();
        topics.forEach(topic -> {
            topicsIds.add(topic.getTopicId());
        });
        return shuffleAnswers(questionRepo.getNRandomQuestions(lang,topicsIds,numOfQuestion));
    }

    private List<Question> shuffleAnswers(List<Question> questions){
        questions.forEach(question -> {
            Collections.shuffle(question.getAnswers());
        });

        return questions;
    }

    private List<Question> getQuestionsByTopicsOfSubject(int lang, int subjectId, int numOfQuestion) throws NotEnoughQuestionsException, NotEnoughTopicException, NotEnoughTopicsWithSufficentQuestions{

        if(!isEnoughTopic(subjectId)){
            throw  new NotEnoughTopicException(subjectId);
        }

        List<Topic> randomizedTopics = getRandomizedTopicsBySubjectId(lang,subjectId);
        List<Question> randomizedQuestions = new ArrayList<>();

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

//        if(randomizedQuestions.size() < 36)
//            throw new NotEnoughQuestionsException("subject",subjectId);

        return randomizedQuestions;
    }

    public List<Question> getQuestionsByTopic(int lang, int topicId, int numOfQuestionAsked) throws NotEnoughQuestionsException{

        List<Question> allQuestionsByTopic = questionRepo.getQuestionsByLangAndTopic_TopicId(lang, topicId);

        if(allQuestionsByTopic.size() == 0){
            throw new NotEnoughQuestionsException("topic",topicId);
        }

        if(allQuestionsByTopic.size() < numOfQuestionAsked) {
            return allQuestionsByTopic;
        }

        Collections.shuffle(allQuestionsByTopic);
        List<Question> randomizedQuestions = allQuestionsByTopic.subList(0,numOfQuestionAsked);

        for(Question question: randomizedQuestions){
            Collections.shuffle(question.getAnswers());
        }
        return randomizedQuestions;

    }

    private List<Topic> getRandomizedTopicsBySubjectId(int lang, int subjectId) throws NotEnoughTopicsWithSufficentQuestions{

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

    public List<Question> getQuestionsByTopicsOfSubject(int subjectId, int lang){
        return questionRepo.findQuestionsByTopic_Subject_SubjectIdAndLang(subjectId,lang);
    }

    public List<Question> getQuestionsByTopic(int topicId, int lang){
        return questionRepo.findQuestionsByTopic_TopicIdAndLang(topicId,lang);
    }

//    private List<Question> shuffleAnswers(List<Question> questions){
//        for(Question question: questions){
//            Collections.shuffle(question.getAnswers());
//        }
//        return questions;
//    }
//    private List<Question> shuffleAndPickConfiguredNumberOfQuestions(Topic topic, List<Question> questions) throws NotEnoughQuestionsException {
//
//        if(questions.size() < topic.getQuestionConfig()) throw new NotEnoughQuestionsException("topic",topic.getTopicId());
//
//        if(questions.size() == topic.getQuestionConfig()) return shuffleAnswers(questions);
//
//        Collections.shuffle(questions);
//
//        List<Question> randomizedQuestions = questions.subList(0,topic.getQuestionConfig());
//
//        return shuffleAnswers(randomizedQuestions);
//    }
//    private List<Question> getRequiredNumberOfQuestionsByLangAndSubjectId(int lang, int subjectId, int numOfQuestionAsked) throws NotEnoughQuestionsException{
//        List<Topic> randomizedTopics = topicRepo.findTopicsBySubject_SubjectId(subjectId);
//        Collections.shuffle(randomizedTopics);
//
//        List<Question> randomizedQuestions = new ArrayList<>();
//
//        for(Topic topic: randomizedTopics){
//            List<Question> allQuestionsByTopic = questionRepo.getQuestionsByLangAndTopic_TopicId(lang, topic.getTopicId());
//            List<Question> randomizedQuestionsByTopic = shuffleAndPickConfiguredNumberOfQuestions(topic, allQuestionsByTopic);
//
//            for(Question question: randomizedQuestionsByTopic){
//
//                if(numOfQuestionAsked == randomizedQuestions.size())
//                    return randomizedQuestions;
//
//                randomizedQuestions.add(question);
//            }
//        }
//
//        if(randomizedQuestions.size() < numOfQuestionAsked){
//            throw new NotEnoughQuestionsException("subject", subjectId);
//        }else{
//            return randomizedQuestions;
//        }
//    }

    @Override
    public int count() {
        return questionRepo.countByQuestionIdGreaterThan(0);
    }

    @Override
    public List findTop10ById(int id) {
        if(id == 0)
            return questionRepo.getQuestionsByQuestionId(id);

        return questionRepo.getQuestionsByQuestion_Id(id);
    }

    @Override
    public List findBySearchText(String searchText) {
        List list = questionRepo.findAllByQuestionTextContains(searchText);
        list.addAll(questionRepo.findAllByDescriptionContains(searchText));
        return list;
    }

    public List<Question> getPageQuestions(int startId){
        if(startId == 0)
            return questionRepo.getQuestionsByQuestionId(startId);

        return  questionRepo.getQuestionsByQuestion_Id(startId);
    }

    @Override
    public List findFirst() {
        return null;
    }

    @Override
    public List findLast() {
        return null;
    }
}
