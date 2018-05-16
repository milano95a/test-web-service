package home.controller;

import home.entity.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @GetMapping("/getsubjects")
    @ResponseBody
    Object getSubjects() {
        return subjectService.getAll();
    }

    @GetMapping("/gettopics/{subject}")
    @ResponseBody
    Object getTopics(@PathVariable String subject) {
        Subject dbSubject = subjectService.getSubjectBySubjectText(subject);
        return topicRepo.getBySubject_SubjectId(dbSubject.getSubjectId());
    }
}
