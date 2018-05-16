package home.repo;

import home.entity.Ex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AB on 07-Sep-17.
 */

@Repository
public interface IExRepo extends JpaRepository<Ex,Integer> {}
