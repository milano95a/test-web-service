package home.model.view;

import home.entity.Course;
import home.uploader.storage.StorageService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static home.constant.Constants.IMAGE_DOMAIN;

public @Data @NoArgsConstructor class CourseView {

    public Integer id;
    public String courseName;
    String courseAddress;
    public String latitude;
    public String longitude;
    public String coursePhone;
    public MultipartFile courseImg;
    public String courseImgPath;
    public String courseRating;

    public CourseView(Course course) {
        this.id = course.getCourseId();
        this.courseName = course.getCourseName();
        this.latitude = course.getLatitude();
        this.longitude = course.getLongitude();
        this.coursePhone = course.getCoursePhone();

        if(course.getCourseImg() != null){
            this.courseImgPath = IMAGE_DOMAIN +  course.getCourseImg();
        }
    }
}
