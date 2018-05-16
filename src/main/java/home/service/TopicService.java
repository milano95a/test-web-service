package home.service;

import home.entity.Topic;
import home.repo.ITopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static home.constant.Constants.*;

@Service
public class TopicService implements BaseService{

    private ITopicRepo topicRepo;

    @Autowired
    public TopicService(ITopicRepo topicRepo){
        this.topicRepo = topicRepo;
    }

    public boolean checkQuestionConfiguration(Topic topic){
        List<Topic> topics = topicRepo.findAll();
        int count = 0;

        for(Topic t: topics){
            count += t.getQuestionConfig();
        }

        if(count > 36)
            return false;

        return true;
    }

    @Override
    public int count() {
        return topicRepo.countByTopicIdGreaterThan(0);
    }

    @Override
    public List findTop10ById(int id) {
        return topicRepo.findTop10ByTopicIdGreaterThan(id);
    }

    public List findTopicsBySubjectId(int subjectId){
        if(subjectId == MATH || subjectId == GEOMETRY){
            List<Topic> topics = new ArrayList<>();
            List<Topic> math = topicRepo.findTopicsBySubject_SubjectId(1);
            List<Topic> geometry = topicRepo.findTopicsBySubject_SubjectId(10);
            topics.addAll(math);
            topics.addAll(geometry);
            return topics;
        }else if(subjectId == UZBEK || subjectId == LITERATURE){
            List<Topic> topics = new ArrayList<>();
            List<Topic> uzbek = topicRepo.findTopicsBySubject_SubjectId(7);
            List<Topic> literature = topicRepo.findTopicsBySubject_SubjectId(11);
            topics.addAll(uzbek);
            topics.addAll(literature);
            return topics;
        }else if(subjectId == HISTORY || subjectId == UZBEK_HISTORY){
            List<Topic> topics = new ArrayList<>();
            List<Topic> history = topicRepo.findTopicsBySubject_SubjectId(6);
            List<Topic> uzbekHistory = topicRepo.findTopicsBySubject_SubjectId(12);
            topics.addAll(history);
            topics.addAll(uzbekHistory);
            return topics;
        }else{
            return topicRepo.findTopicsBySubject_SubjectId(subjectId);
        }
    }

    @Override
    public List findBySearchText(String searchText) {
        return null;
    }

    @Override
    public List findFirst() {
        return null;
    }

    @Override
    public List findLast() {
        return null;
    }

    public Topic getTopicByTopicName(String topicName){
        return topicRepo.findTop1ByTopicOrRuTopic(topicName,topicName);
    }
}
