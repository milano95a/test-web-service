package home.model;

import home.entity.Subject;
import lombok.Data;

import java.util.HashMap;

public @Data class UniversityRequest {
    private HashMap mark;
    private Subject subjectTop;
    private Subject subjectMid;
    private Subject subjectBot;
}
