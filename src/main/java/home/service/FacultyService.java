package home.service;

import home.entity.Faculty;
import home.entity.Subject;
import home.exception.EntryNotFoundException;
import home.repo.IFacultyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by AB on 31-Jul-17.
 */

@Service
public class FacultyService implements BaseService{

    @Autowired
    SubjectService subjectService;

    @Autowired
    IFacultyRepo facultyRepo;

    public List<Faculty> getFacultiesUserCanGetIn(int s1, int s2, int s3, HashMap<Integer,Integer> myScore) throws EntryNotFoundException{
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subjectService.getSubjectById(s1));
        subjects.add(subjectService.getSubjectById(s2));
        subjects.add(subjectService.getSubjectById(s3));

        List<Faculty> faculties = facultyRepo.getFacultiesBySubjectTopInAndSubjectMidInAndSubjectBotIn(subjects,subjects,subjects);

        return faculties
                .stream()
                .filter(faculty -> {
                    int subjectTop = faculty.getSubjectTop().getSubjectId();
                    int subjectMid = faculty.getSubjectMid().getSubjectId();
                    int subjectBot = faculty.getSubjectBot().getSubjectId();
                    faculty.getSubjectTop().setTopics(null);
                    faculty.getSubjectMid().setTopics(null);
                    faculty.getSubjectBot().setTopics(null);
                    double s = myScore.get(subjectTop) * 3.1 + myScore.get(subjectMid) * 2.1 + myScore.get(subjectBot) * 1.1;

                    double score = (double)Math.round(s * 10d)/10d;

                    faculty.setUserScore(score);
                    return score >= faculty.getContractScore();
                }).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return facultyRepo.countByFacultyIdGreaterThan(0);
    }

    @Override
    public List findTop10ById(int id) {
        return facultyRepo.findTop10ByFacultyIdGreaterThan(id);
    }

    @Override
    public List findBySearchText(String searchText) {
        return null;
    }

    @Override
    public List findFirst() {
        return null;
    }

    @Override
    public List findLast() {
        return null;
    }
}
