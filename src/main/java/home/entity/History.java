package home.entity;

import home.model.HistoryType;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class History {

    @Id
    @GeneratedValue
    @Column(name = "HISTORY_ID")
    Integer historyId;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    Question question;
    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    Subject subject;
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    Answer myAnswer;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;
    Integer sessionId;
    HistoryType historyType;
    Long date;

}
