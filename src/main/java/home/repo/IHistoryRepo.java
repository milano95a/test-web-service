package home.repo;

import home.entity.History;
import home.model.HistoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IHistoryRepo extends JpaRepository<History,Integer>{

    @Query(value = "SELECT max(session_id) FROM history", nativeQuery = true)
    Integer getHistoryByMaxSessionId();

    List<History> findHistoriesByHistoryTypeAndUser_UserId(HistoryType historyType, String userId);

    List<History> findHistoriesByHistoryType(HistoryType historyType);

    void deleteAllByQuestionQuestionId(int id);

    List<History> findAllByQuestion_QuestionId(int id);
}
