package home.repo;

import home.entity.Question;
import home.entity.QuestionQA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AB on 01-Aug-17.
 */
public interface IQuestionQARepo extends JpaRepository<QuestionQA,Integer>{

    List<QuestionQA> findTop10ByQuestionIdGreaterThan(int i);

    Integer countByQuestionIdGreaterThan(int i);
}
