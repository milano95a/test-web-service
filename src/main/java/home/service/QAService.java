package home.service;

import home.repo.IQuestionQARepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AB on 23-Aug-17.
 */

@Service
public class QAService implements BaseService {

    @Autowired
    IQuestionQARepo questionQARepo;

    @Override
    public int count() {
        return questionQARepo.countByQuestionIdGreaterThan(0);
    }

    @Override
    public List findTop10ById(int id) {
        return questionQARepo.findTop10ByQuestionIdGreaterThan(id);
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
