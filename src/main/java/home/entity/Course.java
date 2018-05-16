package home.entity;

import home.model.view.CourseView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public @Data @NoArgsConstructor class Course {

    @Id
    @GeneratedValue
    @Column(name = "COURSE_ID")
    Integer courseId;

    @Column(columnDefinition="LONGTEXT")
    String courseName;
    String courseAddress;
    String latitude;
    String longitude;
    String coursePhone;
    String courseImg;

    public Course(CourseView courseView) {
        this.courseName = courseView.getCourseName();
        this.courseAddress = courseView.getCourseAddress();
        this.latitude = courseView.getLatitude();
        this.longitude = courseView.getLongitude();
        this.coursePhone = courseView.getCoursePhone();
    }

    public Course(CourseView courseView, String imagePath) {
        this.courseName = courseView.getCourseName();
        this.courseAddress = courseView.getCourseAddress();
        this.latitude = courseView.getLatitude();
        this.longitude = courseView.getLongitude();
        this.coursePhone = courseView.getCoursePhone();
        this.courseImg = imagePath;
    }
}