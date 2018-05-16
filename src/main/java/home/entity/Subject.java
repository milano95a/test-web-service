package home.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.model.SubjectType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public @Data class Subject{

    @Id
    @Column(name = "SUBJECT_ID")
    @GeneratedValue
    Integer subjectId;

    @Column(columnDefinition="LONGTEXT")
    String subject;
    String ruSubject;
    SubjectType type;

    @OneToMany(mappedBy = "subject")
    @JsonManagedReference
    List<Topic> topics;
}
