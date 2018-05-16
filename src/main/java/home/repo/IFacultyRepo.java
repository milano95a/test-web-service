package home.repo;

import home.entity.Course;
import home.entity.Faculty;
import home.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AB on 28-Jul-17.
 */

public interface IFacultyRepo extends JpaRepository<Faculty,Integer> {

    List<Faculty> getFacultiesBySubjectTopInAndSubjectMidInAndSubjectBotIn(List<Subject> s1, List<Subject> s2,List<Subject> s3);

    List<Faculty> findTop10ByFacultyIdGreaterThan(int i);

    Integer countByFacultyIdGreaterThan(int i);

    List<Faculty> findAllByUniversityUniversityId(int universityId);

    void deleteAllByUniversityUniversityId(int universityId);

    void deleteByUniversityUniversityId(int universtiyId);

    int countAllByUniversityUniversityId(int universityId);

}
