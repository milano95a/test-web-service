package home.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.entity.Subject;
import home.entity.Topic;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static home.constant.Constants.RUS;
import static home.constant.Constants.UZ;

/**
 * Created by AB on 31-Jul-17.
 */
public class SubjectResponse {

    Integer subjectId;
    String subject;
    SubjectType type;

    public SubjectResponse(Subject subject, int lang) {
        this.subjectId = subject.getSubjectId();
        this.type = subject.getType();

        if(lang == RUS){
            this.subject = subject.getRuSubject();
        }else{
            this.subject = subject.getSubject();
        }
    }

    public SubjectResponse() {
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }
}
