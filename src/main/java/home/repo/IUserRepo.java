package home.repo;

import home.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AB on 30-Jul-17.
 */
public interface IUserRepo extends JpaRepository<User,String>{

    List<User> findTop10ByScoreGreaterThanEqual(int i);

    List<User> findAllByScoreGreaterThanEqualOrderByScoreDesc(int i);

    List<User> findAllByScoreGreaterThanEqualOrderByScoreAsc(int i);

    Integer countByScoreGreaterThanEqual(int i);
}
