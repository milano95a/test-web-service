package home.service;

import home.repo.IUniversityRepo;
import home.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AB on 23-Aug-17.
 */

@Service
public class UniversityService implements BaseService{

    @Autowired
    IUniversityRepo universityRepo;

    @Override
    public int count() {
        return universityRepo.countByUniversityIdGreaterThan(0);
    }

    @Override
    public List findTop10ById(int id) {
        return universityRepo.findTop10ByUniversityIdGreaterThan(id);
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
