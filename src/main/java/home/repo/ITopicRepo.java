package home.repo;

import home.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ITopicRepo extends JpaRepository<Topic,Integer> {

    List<Topic> getBySubject_SubjectId(int subjectId);

    Topic findTop1ByTopicOrRuTopic(String topic,String ruTopic);

    List<Topic> findTop10ByTopicIdGreaterThan(int i);

    Integer countByTopicIdGreaterThan(int i);

    List<Topic> findTopicsBySubject_SubjectId(int subjectId);

    int countAllByTopicId(int topicId);
}
