package home.repo;

import home.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAnswerRepo extends JpaRepository<Answer,Integer> {

    void deleteAnswersByQuestion_QuestionId(int questionId);

    List<Answer> findAllByQuestion_QuestionId(int questionId);
}
