package home.constant;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    public static final int MATH_CONFIG = 30;
    public static final int UZBEK_CONFIG = 30;
    public static final int HISTORY_CONFIG = 30;


    public static final int TOPIC_CONFIG = 1;
    public static final String IMAGE_DOMAIN = "http://api.gotest.uz/image/";
//    public static final String IMAGE_DOMAIN = "http://localhost:8765/image/";

//    Languages
    public static final int RUS = 0;
    public static final String RUS_STRING = "Russian";
    public static final int UZ = 1;
    public static final String UZ_STRING = "Uzbek";

//    PageI count
    public static int PAGE_COUNT = 10;

    public static final int SEARCH_PAGE_COUNT = 1000;

//    Temporary Database state
    public static final int POPULATED = 1;
    public static final int EMPTY = 0;
    public static int DB_STATE = EMPTY;

//    Subjects
    public static final int MATH = 1;
    public static final int PHYSICS = 2;

    public static final int BIOLOGY = 3;
    public static final int CHEMISTRY = 4;
    public static final int GEOGRAPHY = 5;

    public static final int HISTORY = 6;
    public static final int UZBEK = 7;
    public static final int RUSSIAN = 8;
    public static final int ENGLISH  = 9;

    public static final int GEOMETRY  = 10;
    public static final int LITERATURE  = 11;
    public static final int UZBEK_HISTORY  = 12;


    public static final String NO_TEXT = "No text";

    public static boolean CACHING = true;

    public static int NUMBER_OF_USERS_IN_LEADERBOARD = 10;

    public static boolean IS_MOBILE_APP_IN_UPDATE_MODE = false;

//  Forms
    public static final String FAQ_FORM_ADD = "/admin/faq_form";
    public static final String FAQ_FORM_CONTENT = "faq_form";
    public static final String QUESTION_FORM_CONTENT = "question_form";
    public static final String QUESTION_FORM_ADD = "/admin/question_form";
    public static final String TOPIC_FORM_CONTENT = "topic_form";
    public static final String TOPIC_FORM_ADD = "/admin/topic_form";
    public static final String COURSE_FORM_CONTENT = "course_form";
    public static final String COURSE_FORM_ADD = "/admin/course_form";
    public static final String UNIVERSITY_FORM_CONTENT = "university_form";
    public static final String UNIVERSITY_FORM_ADD = "/admin/university_form";
    public static final String FACULTY_FORM_CONTENT = "faculty_form";
    public static final String FACULTY_FORM_ADD = "/admin/faculty_form";


//    Tables
    public static final String TABLE_FAQ = "table_faq";
    public static final String PAGINATION_FAQ = "/admin/faqs/";
    public static final String EDITABLE_FAQ = "/admin/faq/";

    public static final String TABLE_QUESTION = "table_question";
    public static final String PAGINATION_QUESTION = "/admin/questions/";
    public static final String EDITABLE_QUESTION = "/admin/question/";

    public static final String TABLE_COURSES = "table_course";
    public static final String PAGINATION_COURSES = "/admin/courses/";
    public static final String EDITABLE_COURSES = "/admin/course/";

    public static final String TABLE_FACULTIES = "table_faculty";
    public static final String PAGINATION_FACULTIES = "/admin/faculties/";
    public static final String EDITABLE_FACULTIES = "/admin/faculty/";

    public static final String TABLE_TOPICS = "table_topic";
    public static final String PAGINATION_TOPICS = "/admin/topics/";
    public static final String EDITABLE_TOPICS = "/admin/topic/";

    public static final String TABLE_UNIVERSITIES = "table_university";
    public static final String PAGINATION_UNIVERSITIES = "/admin/universities/";
    public static final String EDITABLE_UNIVERSITIES = "/admin/university/";

    public static final String TABLE_USERS = "table_user";
    public static final String PAGINATION_USERS = "/admin/users/";
    public static final String EDITABLE_USERS = "/admin/user/";
    public static final String QUESTION_SEARCH_LINK = "/admin/question/filter";

//    todo;

//    region Regions

    public static final String ANDIJON_STRING = "Andijon viloyati";
    public static final int ANDIJON_ID= 1;
    public static final String BUXORO_STRING = "Buxoro viloyati";
    public static final int BUXORO_ID= 2;
    public static final String FARGONA_STRING = "Farg'ona viloyati";
    public static final int FARGONA_ID = 3;
    public static final String JIZZAX_STRING = "Jizzax viloyati";
    public static final int JIZZAX_ID = 4;
    public static final String QASHQADARYO_STRING = "Qashqadaryo viloyati";
    public static final int QASHQADARYO_ID = 5;
    public static final String XORAZM_STRING = "Xorazm viloyati";
    public static final int XORAZM_ID = 6;
    public static final String NAVOIY_STRING = "Navoiy viloyati";
    public static final int NAVOIY_ID = 7;
    public static final String NAMANGAN_STRING = "Namangan viloyati";
    public static final int NAMANGAN_ID = 8;
    public static final String SAMARQAND_STRING = "Samarqand viloyati";
    public static final int SAMARQAND_ID = 9;
    public static final String SURXANDARYO_STRING = "Surxandaryo viloyati";
    public static final int SURXANDARYO_ID = 10;
    public static final String SIRDARYO_STRING = "Sirdaryo viloyati";
    public static final int SIRDARYO_ID = 11;
    public static final String TASHKENT_STRING = "Toshkent viloyati";
    public static final int TASHKENT_ID = 12;
    public static final String TASHKENT_CITY_STRING = "Toshkent shahri";
    public static final int TASHKENT_CITY_ID = 13;

    public static final ArrayList<String> REGIONS = new ArrayList<>(Arrays.asList(new String[]{
                    ANDIJON_STRING,
                    BUXORO_STRING,
                    FARGONA_STRING,
                    JIZZAX_STRING,
                    QASHQADARYO_STRING,
                    XORAZM_STRING,
                    NAVOIY_STRING,
                    NAMANGAN_STRING,
                    SAMARQAND_STRING,
                    SURXANDARYO_STRING,
                    SIRDARYO_STRING,
                    TASHKENT_STRING,
                    TASHKENT_CITY_STRING}));

//    endregion
}
