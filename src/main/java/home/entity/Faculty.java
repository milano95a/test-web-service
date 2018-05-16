package home.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import home.model.view.FacultyView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACULTY_ID")
    Integer facultyId;

    @Column(columnDefinition="LONGTEXT")
    String name;
    String ruName;

    Double grantScore;
    Double contractScore;

    Integer contractPlace;
    Integer grantPlace;

    Double userScore;

    @ManyToOne
    @JoinColumn(name = "UNIVERSITY_ID")
    @JsonBackReference
    University university;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_TOP_ID")
    Subject subjectTop;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_MID_ID")
    Subject subjectMid;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_BOTTOM_ID")
    Subject subjectBot;

    public Faculty(FacultyView facultyView, University university, Subject topSubject, Subject midSubject, Subject botSubject) {
        this.name = facultyView.getFacultyName();
        this.ruName = facultyView.getFacultyRuName();
        this.contractPlace = facultyView.getContractPlace();
        this.contractScore = facultyView.getContractScore();
        this.grantPlace = facultyView.getGrantPlace();
        this.grantScore = facultyView.getGrantScore();
        this.university = university;
        this.subjectTop = topSubject;
        this.subjectMid = midSubject;
        this.subjectBot = botSubject;
    }

    public Faculty() {
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuName() {
        return ruName;
    }

    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    public Double getGrantScore() {
        return grantScore;
    }

    public void setGrantScore(Double grantScore) {
        this.grantScore = grantScore;
    }

    public Double getContractScore() {
        return contractScore;
    }

    public void setContractScore(Double contractScore) {
        this.contractScore = contractScore;
    }

    public Integer getContractPlace() {
        return contractPlace;
    }

    public void setContractPlace(Integer contractPlace) {
        this.contractPlace = contractPlace;
    }

    public Integer getGrantPlace() {
        return grantPlace;
    }

    public void setGrantPlace(Integer grantPlace) {
        this.grantPlace = grantPlace;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Subject getSubjectTop() {
        return subjectTop;
    }

    public void setSubjectTop(Subject subjectTop) {
        this.subjectTop = subjectTop;
    }

    public Subject getSubjectMid() {
        return subjectMid;
    }

    public void setSubjectMid(Subject subjectMid) {
        this.subjectMid = subjectMid;
    }

    public Subject getSubjectBot() {
        return subjectBot;
    }

    public void setSubjectBot(Subject subjectBot) {
        this.subjectBot = subjectBot;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }
}
