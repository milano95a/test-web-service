package home.model;

import home.entity.Question;
import home.entity.Subject;
import lombok.Data;

import java.util.List;

public @Data class Exam {
    SubjectResponse subjectTop;
    List<Question> questionTop;

    SubjectResponse subjectMid;
    List<Question> questionMid;

    SubjectResponse subjectBot;
    List<Question> questionBot;
}
