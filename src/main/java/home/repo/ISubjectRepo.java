package home.repo;

import home.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AB on 28-Jul-17.
 */

public interface ISubjectRepo extends JpaRepository<Subject,Integer>{
    Subject findTop1SubjectBySubjectOrRuSubject(String subject,String ruSubject);
}
