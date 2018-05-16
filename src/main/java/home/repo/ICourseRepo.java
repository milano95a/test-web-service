package home.repo;

import home.entity.Course;
import home.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AB on 01-Aug-17.
 */

public interface ICourseRepo extends JpaRepository<Course, Integer>{

    List<Course> findTop10ByCourseIdGreaterThan(int i);

    Integer countByCourseIdGreaterThan(int i);
}
