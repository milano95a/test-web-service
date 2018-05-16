package home.model;

import home.entity.Question;
import home.entity.Subject;
import home.entity.Topic;
import lombok.Data;

import java.util.List;

public @Data class Prep {

    Subject subject;
    Topic topic;
    List<Question> question;

}
