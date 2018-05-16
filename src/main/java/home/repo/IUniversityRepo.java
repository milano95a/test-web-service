package home.repo;

import home.entity.Faculty;
import home.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface IUniversityRepo extends JpaRepository<University,Integer> {

    Set<University> getUniversitiesByFacultiesIn(List<Faculty> faculties);

    University findUniversityByName(String universityName);

    List<University> findTop10ByUniversityIdGreaterThan(int i);

    Integer countByUniversityIdGreaterThan(int i);

    List<University> findAllByRegion(String region);
}
