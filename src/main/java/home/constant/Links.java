package home.constant;

import lombok.Data;

public @Data class Links {

    public static final String forgot = "/admin/forgot";
    public static final String logout = "/logout";
    public static final String help = "/help";

    public static final String questions = "/admin/questions/1";
    public static final String courses = "/admin/courses/1";
    public static final String faculties = "/admin/faculties/1";
    public static final String faqs = "/admin/faqs/1";
    public static final String topics = "/admin/topics/1";
    public static final String universities = "/admin/universities/1";
    public static final String users = "/admin/users/1";

//    Side Forms
    public static final String topic_form = "/admin/topic_form";
    public static final String question_form = "/admin/question_form";
    public static final String university_form = "/admin/university_form";
    public static final String faculty_form = "/admin/faculty_form";
    public static final String faq_form_update = "/admin/faq_form_update/";
    public static final String course_form = "/admin/course_form";
    public static final String faq_form = "/admin/faq_form";

//    Search
    public static final String question_search = "/admin/question/search";

//    Filter
    public String form_filter = "/undefined";

    public String form_submit = "/undefined";
    public String content = "/undefined";
    public String pagination = "/undefined";
    public String editable = "/undefined";
    public String cancel = "/undefined";
}