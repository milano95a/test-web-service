package home.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.model.view.UniversityView;
import javax.persistence.*;
import java.util.List;

@Entity
public class University {

    @Id
    @Column(name = "UNIVERSITY_ID")
    @GeneratedValue
    Integer universityId;

    @Column(columnDefinition="LONGTEXT")
    String name;
    @Column(columnDefinition="LONGTEXT")
    String ruName;
    String universityImg;
    String latitude;
    String longitude;
    String universityPhone;
    String region;

    @OneToMany(mappedBy = "university")
    @JsonManagedReference
    List<Faculty> faculties;

    public University(UniversityView universityView, String imagePath){
        this.name = universityView.getUniversityName();
        this.ruName = universityView.getRuUniversityName();
        this.universityImg = imagePath;
        this.latitude = universityView.getLatitude();
        this.longitude = universityView.getLongitude();
        this.universityPhone = universityView.getPhone();
        this.region = universityView.getRegion();
    }

    public University(UniversityView universityView){
        this.name = universityView.getUniversityName();
        this.ruName = universityView.getRuUniversityName();
        this.latitude = universityView.getLatitude();
        this.longitude = universityView.getLongitude();
        this.universityPhone = universityView.getPhone();
        this.region = universityView.getRegion();
    }

    public University() {
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUniversityImg() {
        return universityImg;
    }

    public void setUniversityImg(String universityImg) {
        this.universityImg = universityImg;
    }

    public String getUniversityPhone() {
        return universityPhone;
    }

    public void setUniversityPhone(String universityPhone) {
        this.universityPhone = universityPhone;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
