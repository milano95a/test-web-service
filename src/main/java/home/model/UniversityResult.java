package home.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import home.entity.Faculty;
import home.entity.University;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AB on 31-Jul-17.
 */
public class UniversityResult {

    Integer universityId;
    String name;
    String ruName;
    String latitude;
    String longitude;
    String phone;
    List<Faculty> faculties;

    public UniversityResult(University university, List<Faculty> allFaculties) {
        universityId = university.getUniversityId();
        name = university.getName();
        ruName = university.getRuName();
        latitude = university.getLatitude();
        longitude = university.getLongitude();
        phone = university.getUniversityPhone();
        faculties = new ArrayList<>();

        for(Faculty f: allFaculties){
            if(f.getUniversity().getUniversityId().equals(universityId)){
                faculties.add(f);
            }
        }
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
