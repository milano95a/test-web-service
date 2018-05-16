package home.model.view;

import home.entity.Faculty;

/**
 * Created by AB on 21-Aug-17.
 */
public class FacultyView {

    Integer id;
    String universityName;
    String facultyName;
    String facultyRuName;
    String topSubject;
    String midSubject;
    String botSubject;
    Integer grantPlace;
    Integer contractPlace;
    Double grantScore;
    Double contractScore;

    public FacultyView(Faculty faculty) {
        this.id = faculty.getFacultyId();
        this.universityName = faculty.getUniversity().getName();
        this.facultyName = faculty.getName();
        this.facultyRuName = faculty.getRuName();
        this.topSubject = faculty.getSubjectTop().getSubject();
        this.midSubject = faculty.getSubjectMid().getSubject();
        this.botSubject = faculty.getSubjectBot().getSubject();
        this.grantPlace = faculty.getGrantPlace();
        this.contractPlace = faculty.getContractPlace();
        this.grantScore = faculty.getGrantScore();
        this.contractScore = faculty.getContractScore();
    }

    public FacultyView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyRuName() {
        return facultyRuName;
    }

    public void setFacultyRuName(String facultyRuName) {
        this.facultyRuName = facultyRuName;
    }

    public Double getContractScore() {
        return contractScore;
    }

    public void setContractScore(Double contractScore) {
        this.contractScore = contractScore;
    }

    public Double getGrantScore() {
        return grantScore;
    }

    public void setGrantScore(Double grantScore) {
        this.grantScore = grantScore;
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

    public String getTopSubject() {
        return topSubject;
    }

    public void setTopSubject(String topSubject) {
        this.topSubject = topSubject;
    }

    public String getMidSubject() {
        return midSubject;
    }

    public void setMidSubject(String midSubject) {
        this.midSubject = midSubject;
    }

    public String getBotSubject() {
        return botSubject;
    }

    public void setBotSubject(String botSubject) {
        this.botSubject = botSubject;
    }
}
