package home.constant;

public class UzLang extends Lang{

    //    region General
    public String latitude = "Latitude";
    public String longitude = "Longitude";
    public String phone = "Phone";
    public String rating = "Rating";
    public String filter = "Filter";
    public String search = "Search";

    public String add_new_question = "Add Question";
    public String add_new_uni= "Add University";
    public String add_new_course= "Add Course";
    public String add_new_faculty= "Add Faculty";
    public String add_new_faq= "Add FAQ";
    public String add_new_topic= "Add Topic";

//    endregion

    //    region Content
    public String contentTitle = "undefined";
    public String edit = "Edit";
    public String delete = "Delete";
    public String selectSubject = "Select Subject";
    public String selectLanguage = "Select Language";
    public String selectTopic = "Select Topic";
    public String selectUniversity = "Select University";

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }
//    endregion

    //    region Navigation Bar
    public String navBarLogOut = "Log out";
//    endregion

    //    region Pagination
    public final String previous = "Previous";
    public final String next = "Next";
    public final String first = "First";
    public final String last = "Last";
//    endregion

    //  region  Side
    public final String sideAppName = "Gotest.uz";
    public String sideWelcome = "Welcome,";
    public String sideGeneral = "General";

//    Side Table
    public String sideTable = "Tables";
    public String sideQuestions = "Questions";
    public String sideCourses = "Courses";
    public String sideFaculties = "Faculties";
    public String sideQAs = "FAQ";
    public String sideTopics = "Topics";
    public String sideUniversities = "Universities";
    public String sideUsers = "Users";

//    Side Forms
    public String sideForm = "Forms";
    public String sideFormTopic = "Topic";
    public String sideFormQuestion = "Question";
    public String sideFormUniversity = "University";
    public String sideFormFaculty = "Faculty";
    public String sideFormCourse = "Course";
    public String sideFormFAQ = "FAQ";

//    Submit
    public String submit = "Submit";
    public String reset = "Reset";
    public String cancel = "Cancel";

//    Login
    public String login = "Log in";
    public String loginForm = "Login Form";
    public String loginUsername= "Username";
    public String loginPassword= "Password";
    public String loginForgot= "Forgot password?";
    public String loginRestore= "Restore";
    public String loginRestoreAccount= "Restore Account";
    public String loginEmail= "Email";
    public String loginAlreadyMember= "Already Member";


//    endregion

    //    region Forms
//    Course
//    Faculty
    public String facultyUniversity = "Select University";
    public String facultyName = "Faculty Name";
    public String facultyRuName = "Faculty Russian Name";
    public String facultyTopSubject = "Faculty Top Subject";
    public String facultyMidSubject = "Faculty Mid Subject";
    public String facultyBotSubject = "Faculty Bot Subject";
    public String facultyGantPlace = "Faculty Grant Place";
    public String facultyContractPlace = "Faculty Contract Place";
    public String facultyGrantScore = "Faculty Grant Score";
    public String facultyContractScore = "Faculty Score Score";
//    FAQ
    public String faqQuestion = "FAQ Question";
    public String faqAnswer = "FAQ Answer";
    public String faqRuQuestion = "FAQ Russian Question";
    public String faqRuAnswer = "FAQ Russian Answer";
//    Question
    public String questionLanguage = "Question Language";
    public String questionSubject = "Question Subject";
    public String questionTopic = "Question Topic";
    public String questionQuestionText= "Question Text";
    public String questionQuestionDesc= "Question Description Text";
    public String questionAnswerA= "Answer A";
    public String questionAnswerB= "Answer B";
    public String questionAnswerC= "Answer C";
    public String questionAnswerD= "Answer D";
    public String selectCorrectAnswer= "Select Correct Answer";
    public String questionA= "A";
    public String questionB= "B";
    public String questionC= "C";
    public String questionD= "D";
//    Topic
    public String topicSubject= "Select Topic";
    public String topicName= "Topic";
    public String topicRuName= "Topic Russian Name";
    public String topicQuestionConfig = "Topic Config";
//    University
    public String universityName = "University Name";
    public String universityRuName = "University Russian Name";
//    endregion

    //    region Table

//    Course
    public String courseName = "Course Name";
//    Faculty
    public String facultyTableFaculty = "Faculty";
    public String facultyTableUniversity= "University";
    public String facultyTableTopSubject= "Top Subject";
    public String facultyTableMidSubject= "Mis Subject";
    public String facultyTableBotSubject= "Bot Subject";
    public String facultyTableGrantPlace= "Grant Place";
    public String facultyTableContractPlace= "Contract Place";
    public String facultyTableGrantScore= "Grant Score";
    public String facultyTableContractScore= "Faculty";
//    FAQ
    public String faqTableQuestion = "Question";
    public String faqTableAnswer = "Answer";
    public String faqTableRuQuestion = "Rusian Question";
    public String faqTableRuAnswer = "Russian Answer";
//    Question
    public String questionTableQuestion= "Question";
    public String questionTableLanguage= "Language";
    public String questionTableSubject= "Subject";
    public String questionTableTopic= "Topic";
//    Topic
    public String topicTableTopic = "Topic";
    public String topicTableRuTopic = "Russian Topic";
    public String topicTableSubejct = "Subject";
    public String topicTableConfig = "Questions Uz/Rus";
//    University
    public String universityTableUniversity = "University";
    public String universityTableRuUniversity= "University Russian";
    public String universityRegion = "Region";
    public String selectRegion = "Select Region";
    //    User
    public String userTableUserId= "User id";
    public String userTableUserName= "User name";
    public String userTableUserCreateDate= "Create Date";
    public String userTableUserLoginType= "Login Type";
    public String userTableUserScore= "Score";
//    endregion

    public static final String TABLE_USERS = "Users";
    public static final String TABLE_QUESTIONS = "Questions";
    public static final String TABLE_COURSES= "Courses";
    public static final String TABLE_FACULTIES= "Faculties";
    public static final String TABLE_FAQS= "Faqs";
    public static final String TABLE_TOPICS= "Topics";
    public static final String TABLE_UNIVERSITIES= "Universities";

    public static final String FORM_QUESTIONS = "Question form";
    public static final String FORM_COURSES= "Coruse form";
    public static final String FORM_FACULTIES= "Faculty form";
    public static final String FORM_FAQS= "Faq form";
    public static final String FORM_TOPICS= "Topic form";
    public static final String FORM_UNIVERSITIES= "University form";
}
