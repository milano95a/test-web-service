package home.service;

import home.repo.ICourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AB on 22-Aug-17.
 */
@Service
public class CourseService implements BaseService {

    @Autowired
    ICourseRepo courseRepo;

    @Override
    public int count() {
        return courseRepo.countByCourseIdGreaterThan(0);
    }

    @Override
    public List findTop10ById(int id) {
        return courseRepo.findTop10ByCourseIdGreaterThan(id);
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
