package home.repo;

import home.entity.Question;
import home.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IQuestionRepo extends JpaRepository<Question,Integer> {

    List<Question> getQuestionsByLangAndTopic_TopicId(int lang, int topicId);

    List<Question> getQuestionsByTopic_Subject_SubjectId(int topicId);

    @Query(value = "SELECT * FROM question WHERE question_id > (?#{[0]}) ORDER BY question_id DESC limit 10;", nativeQuery = true)
    List<Question> getQuestionsByQuestionId(int id);

    @Query(value = "select * from question where question_id < (?#{[0]}) order by question_id desc limit 10;", nativeQuery = true)
    List<Question> getQuestionsByQuestion_Id(int id);

//    List<Question> findTop10ByQuestionIdGreaterThan(int i);
//    List<Question> findLast10ByQuestionIdGreaterThanEqual(int i);

    List<Question> findAllByQuestionTextContains(String searchText);

    List<Question> findAllByDescriptionContains(String searchText);

    Integer countByQuestionIdGreaterThan(int i);

    List<Question> findQuestionsByTopicTopicId(int id);

    void deleteQuestionsByTopic_TopicId(int topicId);

    void removeQuestionsByQuestionId(int questionId);

    List<Question> findQuestionsByTopic_Subject_SubjectIdAndLang(int subjectId, int lang);

    List<Question> findQuestionsByTopic_TopicIdAndLang(int topicId, int lang);

    int countQuestionsByTopic_TopicIdAndLang(int topicId, int lang);

    @Query(nativeQuery = true, value = "SELECT * FROM question WHERE lang = (?#{[0]}) and topic_id in (?#{[1]}) ORDER BY RAND() LIMIT ?#{[2]}")
    List<Question> getNRandomQuestions(int lang, List<Integer> topicIds, int numOfQuestions);
}
