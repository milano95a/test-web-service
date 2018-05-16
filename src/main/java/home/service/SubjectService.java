package home.service;

import home.entity.Subject;
import home.exception.EntryNotFoundException;
import home.repo.ISubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    ISubjectRepo subjectRepo;

    public Subject getSubjectById(int id) throws EntryNotFoundException{
        if(!subjectRepo.exists(id)) throw new EntryNotFoundException("subject", id);
        return subjectRepo.findOne(id);
    }

    public List<Subject> getAll(){
        return subjectRepo.findAll();
    }

    public Subject getById(int id){
        return subjectRepo.findOne(id);
    }

    public Subject getSubjectBySubjectText(String subjectText){
        return subjectRepo.findTop1SubjectBySubjectOrRuSubject(subjectText,subjectText);
    }
}
