package home.model.view;

import home.entity.University;
import org.springframework.web.multipart.MultipartFile;

import static home.constant.Constants.IMAGE_DOMAIN;

/**
 * Created by AB on 20-Aug-17.
 */

public class UniversityView {

    Integer id;
    String universityName;
    String ruUniversityName;
    MultipartFile universityImage;
    String universityImagePath;
    String region;
    String latitude;
    String longitude;
    String phone;

    public UniversityView(University university) {
        this.id = university.getUniversityId();
        this.universityName = university.getName();
        this.ruUniversityName = university.getRuName();
        this.latitude = university.getLatitude();
        this.longitude = university.getLongitude();
        this.phone = university.getUniversityPhone();
        this.region = university.getRegion();

        if(university.getUniversityImg() != null){
            this.universityImagePath = IMAGE_DOMAIN + university.getUniversityImg();
        }

    }

    public UniversityView() {
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

    public String getRuUniversityName() {
        return ruUniversityName;
    }

    public void setRuUniversityName(String ruUniversityName) {
        this.ruUniversityName = ruUniversityName;
    }

    public MultipartFile getUniversityImage() {
        return universityImage;
    }

    public void setUniversityImage(MultipartFile universityImage) {
        this.universityImage = universityImage;
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

    public String getUniversityImagePath() {
        return universityImagePath;
    }

    public void setUniversityImagePath(String universityImagePath) {
        this.universityImagePath = universityImagePath;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
