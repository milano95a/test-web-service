package home.controller.view;

import home.constant.Links;
import home.constant.UzLang;
import home.controller.BaseController;
import home.entity.*;
import home.model.view.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

import static home.constant.Constants.*;

@Controller
@RequestMapping("/admin")
public class AdminUpdateController extends BaseController {

    public static final String FACULTY_FORM_CONTENT = "faculty_form";
    public static final String FACULTY_FORM_UPDATE = "/admin/faculty_form_update/";

    public static final String FAQ_FORM_UPDATE = "/admin/faq_form_update/";
    public static final String FAQ_FORM_CONTENT = "faq_form";

    private static final String QUESTION_FORM_CONTENT = "question_form";
    private static final String QUESTION_FORM_UPDATE = "/admin/question_form_update/";

    private static final String COURSE_FORM_CONTENT = "course_form";
    private static final String COURSE_FORM_UPDATE = "/admin/course_form_update/";
    private static final String TOPIC_FORM_CONTENT = "topic_form";
    private static final String TOPIC_FORM_UPDATE = "/admin/topic_form_update/";
    private static final String UNIVERSITY_FORM_CONTENT = "university_form";
    private static final String UNIVERSITY_FORM_UPDATE = "/admin/university_form_update/";

    private static final String TABLE_FACULTY = "/admin/faculties/";
    private static final String TABLE_UNIVERSITY = "/admin/universities/";
    private static final String TABLE_QUESTION = "/admin/questions/";
    private static final String TABLE_COURSE = "/admin/courses/";
    private static final String TABLE_FAQ = "/admin/faqs/";
    private static final String TABLE_TOPIC = "/admin/topics/";

    @GetMapping("/faculty/{pageNumber}/edit/{id}")
    String getFacultyEditForm(Model model, @PathVariable int pageNumber, @PathVariable int id) {
        Faculty faculty = facultyRepo.findOne(id);
        FacultyView facultyView = new FacultyView(faculty);

        List<Subject> subjects = subjectRepo.findAll();

        List<String> topSubject = new ArrayList<>();
        List<String> midSubject = new ArrayList<>();
        List<String> botSubject = new ArrayList<>();

        for (Subject s : subjectRepo.findAll()) {
            topSubject.add(s.getSubject());
            midSubject.add(s.getSubject());
            botSubject.add(s.getSubject());
        }

        topSubject.remove(facultyView.getTopSubject());
        midSubject.remove(facultyView.getMidSubject());
        botSubject.remove(facultyView.getBotSubject());

        List<University> universities = universityRepo.findAll();
        List<String> universityList = new ArrayList<>();

        for (University u : universities) {
            universityList.add(u.getName());
        }

        universityList.remove(facultyView.getUniversityName());

        Links links = new Links();
        links.setContent(FACULTY_FORM_CONTENT);
        links.setForm_submit(FACULTY_FORM_UPDATE + pageNumber);
        links.setCancel(TABLE_FACULTY + pageNumber);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_UNIVERSITIES);
        model.addAttribute("lang", uzLang);
        model.addAttribute("unis", universityList);
        model.addAttribute("topSubjects", topSubject);
        model.addAttribute("midSubjects", midSubject);
        model.addAttribute("botSubjects", botSubject);
        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("editable", facultyView);
        model.addAttribute("object", new FacultyView());
        model.addAttribute("username", getAdminName());
        return "template";
    }

    @PostMapping("/faculty_form_update/{pageNumber}")
    RedirectView updateFaculty(@PathVariable int pageNumber, @ModelAttribute FacultyView x) {

        Faculty dbFaculty = facultyRepo.findOne(x.getId());

        if (!dbFaculty.getUniversity().getName().equals(x.getUniversityName())) {
            University university = universityRepo.findUniversityByName(x.getUniversityName());
            dbFaculty.setUniversity(university);
        }

        if (!dbFaculty.getSubjectTop().getSubject().equals(x.getTopSubject())) {
            Subject subjectTop = subjectRepo.findTop1SubjectBySubjectOrRuSubject(x.getTopSubject(),x.getTopSubject());
            dbFaculty.setSubjectTop(subjectTop);
        }

        if (!dbFaculty.getSubjectMid().getSubject().equals(x.getMidSubject())) {
            Subject subjectMid = subjectRepo.findTop1SubjectBySubjectOrRuSubject(x.getMidSubject(),x.getMidSubject());
            dbFaculty.setSubjectMid(subjectMid);
        }

        if (!dbFaculty.getSubjectBot().getSubject().equals(x.getBotSubject())) {
            Subject subjectBot = subjectRepo.findTop1SubjectBySubjectOrRuSubject(x.getBotSubject(),x.getBotSubject());
            dbFaculty.setSubjectBot(subjectBot);
        }
        dbFaculty.setName(x.getFacultyName());
        dbFaculty.setRuName(x.getFacultyRuName());
        dbFaculty.setGrantScore(x.getGrantScore());
        dbFaculty.setContractScore(x.getContractScore());
        dbFaculty.setGrantPlace(x.getGrantPlace());
        dbFaculty.setContractPlace(x.getContractPlace());

        facultyRepo.save(dbFaculty);

        return new RedirectView("/admin/faculties/" + pageNumber);
    }

    @GetMapping("/faq/{pageNumber}/edit/{id}")
    String getQAEditForm(Model model, @PathVariable int pageNumber, @PathVariable int id) {
        QuestionQA questionQA = questionQARepo.findOne(id);
        QAView qaView = new QAView(questionQA);

        Links links = new Links();
        links.setContent(FAQ_FORM_CONTENT);
        links.setForm_submit(FAQ_FORM_UPDATE + pageNumber);
        links.setCancel(TABLE_FAQ + pageNumber);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_FAQS);
        model.addAttribute("lang", uzLang);
        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("editable", qaView);
        model.addAttribute("object", new QAView());
        model.addAttribute("username", getAdminName());
        return "template";
    }

    @PostMapping("/faq_form_update/{pageNumber}")
    RedirectView updateQA(@PathVariable int pageNumber, @ModelAttribute QAView q) {

        QuestionQA questionQA = questionQARepo.findOne(q.getId());
        questionQA.setQuestionText(q.getQuestionText());
        questionQA.setRuquestionText(q.getRuquestionText());

        AnswerQA answerQA = questionQA.getAnswer();
        answerQA.setAnswer(q.getAnswer());
        answerQA.setRuanswer(q.getRuanswer());

        answerQARepo.save(answerQA);
        questionQA.setAnswer(answerQA);
        questionQARepo.save(questionQA);

        return new RedirectView("/admin/faqs/" + pageNumber);
    }

    @GetMapping("/question/{pageNumber}/edit/{id}")
    String getQuestionFormUpdate(Model model, @PathVariable int pageNumber, @PathVariable int id) {
        Question question = questionRepo.findOne(id);
        Topic topic = topicRepo.findOne(question.getTopic().getTopicId());
        Subject subject = topic.getSubject();

        QuestionView questionView = new QuestionView(question, subject, topic);
        Links links = new Links();
        links.setContent(QUESTION_FORM_CONTENT);
        links.setForm_submit(QUESTION_FORM_UPDATE + pageNumber);
        links.setCancel(TABLE_QUESTION + pageNumber);

        List<String> subjects = getSubjectNamesByLang(question, subject);
        List<String> topics = getTopicNamesByLang(question, subject, topic);
        List<String> languages = getLanguageStrings(question);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_QUESTIONS);
        model.addAttribute("lang", uzLang);
        model.addAttribute("languages", languages);
        model.addAttribute("topics", topics);
        model.addAttribute("subjects", subjects);
        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("editable", questionView);
        model.addAttribute("object", new QuestionView());
        model.addAttribute("username", getAdminName());
        return "template";
    }

    private List<String> getLanguageStrings(Question question) {
        List<String> languages = new ArrayList<>();
        if (question.getLang() == UZ) {
            languages.add("Russian");
        } else {
            languages.add("Uzbek");
        }
        return languages;
    }

    private List<String> getSubjectNamesByLang(Question question, Subject subject) {
        List<String> subjects = new ArrayList<>();
        List<Subject> dbSubjects = subjectRepo.findAll();
        for (Subject s : dbSubjects) {
            if (question.getLang() == RUS) {
                if (!s.getSubjectId().equals(subject.getSubjectId())) {
                    subjects.add(s.getRuSubject());
                }
            } else {
                if (!s.getSubjectId().equals(subject.getSubjectId())) {
                    subjects.add(s.getSubject());
                }
            }
        }
        return subjects;
    }

    private List<String> getTopicNamesByLang(Question question, Subject subject, Topic topic) {
        List<Topic> dbTopics = topicRepo.findTopicsBySubject_SubjectId(subject.getSubjectId());
        List<String> topics = new ArrayList<>();
        for (Topic t : dbTopics) {
            if (question.getLang() == RUS) {
                if (!topic.getTopicId().equals(t.getTopicId())) {
                    topics.add(t.getRuTopic());
                }
            } else {
                if (!topic.getTopicId().equals(t.getTopicId())) {
                    topics.add(t.getTopic());
                }
            }
        }
        return topics;
    }


    @PostMapping("/question_form_update/{pageNumber}")
    RedirectView postQuestionFormUpdate(@PathVariable int pageNumber, @ModelAttribute QuestionView formObject) {
        Question question = questionRepo.findOne(formObject.getId());

        question = updateQuestion(question,formObject);

        questionRepo.save(question);

        List<Answer> answers = question.getAnswers();

        Answer answerA = answers.get(0);
        answerA = updateAnswer(answerA,
                formObject.getAText(),
                formObject.getIsAImgUpdated(),
                formObject.getAImg(),
                formObject.getCorrect(),
                "a");

//        answerA.setAnswer(formObject.getAText());
//
//        if (formObject.getIsAImgUpdated()) {
//            if (!formObject.getAImg().isEmpty()) {
//                String path = storageService.store(formObject.getAImg());
//                answerA.setAnswerImg(path);
//            } else {
//                answerA.setAnswerImg(null);
//            }
//        }
//
//        if (formObject.getCorrect().equals("a")) {
//            answerA.setStatus(true);
//        } else {
//            answerA.setStatus(false);
//        }
        answerA.setQuestion(question);
        answerRepo.save(answerA);
//        answers.add(answerA);


        Answer answerB = answers.get(1);
        answerB = updateAnswer(answerB,
                formObject.getBText(),
                formObject.getIsBImgUpdated(),
                formObject.getBImg(),
                formObject.getCorrect()
                ,"b");
//        answerB.setAnswer(formObject.getBText());
//
//        if (formObject.getIsBImgUpdated()) {
//            if (!formObject.getBImg().isEmpty()) {
//                String path = storageService.store(formObject.getBImg());
//                answerB.setAnswerImg(path);
//            } else {
//                answerB.setAnswerImg(null);
//            }
//        }
//
//        if (formObject.getCorrect().equals("b")) {
//            answerB.setStatus(true);
//        } else {
//            answerB.setStatus(false);
//        }
        answerB.setQuestion(question);
        answerRepo.save(answerB);
//        answers.add(answerB);


        Answer answerC = answers.get(2);
        answerC = updateAnswer(answerC,
                formObject.getCText(),
                formObject.getIsCImgUpdated(),
                formObject.getCImg(),
                formObject.getCorrect(),
                "c");

//        answerC.setAnswer(formObject.getCText());
//        if (formObject.getIsCImgUpdated()) {
//            if (!formObject.getCImg().isEmpty()) {
//                String path = storageService.store(formObject.getCImg());
//                answerC.setAnswerImg(path);
//            }else{
//                answerC.setAnswerImg(null);
//            }
//        }
//
//        if (formObject.getCorrect().equals("c")) {
//            answerC.setStatus(true);
//        } else {
//            answerC.setStatus(false);
//        }
        answerC.setQuestion(question);
        answerRepo.save(answerC);
//        answers.add(answerC);


        Answer answerD = answers.get(3);
        answerD = updateAnswer(answerD,
                formObject.getDText(),
                formObject.getIsDImgUpdated(),
                formObject.getDImg(),
                formObject.getCorrect(),
                "d");
//        answerD.setAnswer(formObject.getDText());
//        if (formObject.getIsDImgUpdated()) {
//            if (!formObject.getDImg().isEmpty()) {
//                String path = storageService.store(formObject.getDImg());
//                answerD.setAnswerImg(path);
//            }else{
//                answerD.setAnswerImg(null);
//            }
//        }
//        if (formObject.getCorrect().equals("d")) {
//            answerD.setStatus(true);
//        } else {
//            answerD.setStatus(false);
//        }
        answerD.setQuestion(question);
        answerRepo.save(answerD);
//        answers.add(answerD);

        return new RedirectView("/admin/questions/" + pageNumber);
    }

    private Question updateQuestion(Question question, QuestionView formObject){
        int questionLanguage = 1;

        if (formObject.getLanguage().contains(RUS_STRING)) {
            questionLanguage = RUS;
        } else {
            questionLanguage = UZ;
        }

        question.setLang(questionLanguage);
        Topic topic = topicService.getTopicByTopicName(formObject.getTopic());

        question.setTopic(topic);

        if (formObject.getIsQuestionImgUpdated()) {
            if (!formObject.getQuestionImg().isEmpty()) {
                String path = storageService.store(formObject.getQuestionImg());
                question.setQuestionImg(path);
            } else {
                question.setQuestionImg(null);
            }
        }

        question.setQuestionText(formObject.getQuestionText());
        question.setDescription(formObject.getDescText());

        if (formObject.getIsDescriptionImgUpdated()) {
            if (!formObject.getDescImg().isEmpty()) {
                String path = storageService.store(formObject.getDescImg());
                question.setDescriptionImg(path);
            } else {
                question.setDescriptionImg(null);
            }
        }

        return question;
    }

    private Answer updateAnswer(Answer answer, String answerText, boolean isImgUpdated, MultipartFile answerImage, String trueAnswer, String currentAnswer){
        answer.setAnswer(answerText);

        if (isImgUpdated) {
            if (!answerImage.isEmpty()) {
                String path = storageService.store(answerImage);
                answer.setAnswerImg(path);
            } else {
                answer.setAnswerImg(null);
            }
        }

        if(trueAnswer != null){
            if (trueAnswer.equals(currentAnswer)) {
                answer.setStatus(true);
            } else {
                answer.setStatus(false);
            }
        }
        return answer;
    }

    @GetMapping("/course/{pageNumber}/edit/{id}")
    String getCourseFormUpdate(Model model, @PathVariable int pageNumber, @PathVariable int id) {
        Course course = courseRepo.findOne(id);

        CourseView courseView = new CourseView(course);

        Links links = new Links();
        links.setContent(COURSE_FORM_CONTENT);
        links.setForm_submit(COURSE_FORM_UPDATE + pageNumber);
        links.setCancel(TABLE_COURSE + pageNumber);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_COURSES);
        model.addAttribute("lang", uzLang);
        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("editable", courseView);
        model.addAttribute("object", new CourseView());
        model.addAttribute("username", getAdminName());
        return "template";
    }

    @PostMapping("/course_form_update/{pageNumber}")
    RedirectView postCourseFormUpdate(@PathVariable int pageNumber, @ModelAttribute CourseView x) {

        Course dbCourse = courseRepo.findOne(x.getId());

        dbCourse.setCourseName(x.getCourseName());
        dbCourse.setCoursePhone(x.getCoursePhone());
        dbCourse.setLongitude(x.getLongitude());
        dbCourse.setLatitude(x.getLatitude());

        if (!x.getCourseImg().isEmpty()) {
            String imagePath = storageService.store(x.getCourseImg());
            dbCourse.setCourseImg(imagePath);
        }

        courseRepo.save(dbCourse);

        return new RedirectView("/admin/courses/" + pageNumber);
    }

    @GetMapping("/topic/{pageNumber}/edit/{id}")
    String getTopicFormUpdate(Model model, @PathVariable int pageNumber, @PathVariable int id) {

        Topic topic = topicRepo.findOne(id);
        TopicView topicView = new TopicView(topic);

        List<Subject> subjectList = subjectRepo.findAll();
        List<String> subjects = new ArrayList<>();

        for (Subject s : subjectList) {
            subjects.add(s.getSubject());
        }

        subjects.remove(topicView.getSubject());

        Links links = new Links();
        links.setContent(TOPIC_FORM_CONTENT);
        links.setForm_submit(TOPIC_FORM_UPDATE + pageNumber);
        links.setCancel(TABLE_TOPIC + pageNumber);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_TOPICS);
        model.addAttribute("lang", uzLang);
        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("subjects", subjects);
        model.addAttribute("editable", topicView);
        model.addAttribute("object", new TopicView());
        model.addAttribute("username", getAdminName());
        return "template";
    }

    @PostMapping("/topic_form_update/{pageNumber}")
    RedirectView postTopicFormUpdate(@PathVariable int pageNumber, @ModelAttribute TopicView x) {

        Topic dbTopic = topicRepo.findOne(x.getId());
        dbTopic.setTopic(x.getTopic());
        dbTopic.setRuTopic(x.getRuTopic());

        Subject subject = subjectRepo.findTop1SubjectBySubjectOrRuSubject(x.getSubject(),x.getSubject());
        dbTopic.setSubject(subject);
        topicRepo.save(dbTopic);

        return new RedirectView("/admin/topics/" + pageNumber);
    }

    @GetMapping("/university/{pageNumber}/edit/{id}")
    String getUniversityFormUpdate(Model model, @PathVariable int pageNumber, @PathVariable int id) {

        University university = universityRepo.findOne(id);
        UniversityView universityView = new UniversityView(university);

        Links links = new Links();
        links.setContent(UNIVERSITY_FORM_CONTENT);
        links.setForm_submit(UNIVERSITY_FORM_UPDATE + pageNumber);
        links.setCancel(TABLE_UNIVERSITY + pageNumber);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_UNIVERSITIES);
        model.addAttribute("lang", uzLang);
        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("region",getRegions());
        model.addAttribute("editable", universityView);
        model.addAttribute("object", new UniversityView());
        model.addAttribute("username", getAdminName());
        return "template";
    }

    @PostMapping("/university_form_update/{pageNumber}")
    RedirectView postUniversityFormUpdate(@PathVariable int pageNumber, @ModelAttribute UniversityView x) {

        University dbUniversity = universityRepo.findOne(x.getId());

        dbUniversity.setName(x.getUniversityName());
        dbUniversity.setRuName(x.getRuUniversityName());
        dbUniversity.setUniversityPhone(x.getPhone());
        dbUniversity.setLatitude(x.getLatitude());
        dbUniversity.setLongitude(x.getLongitude());
        dbUniversity.setUniversityId(x.getId());
        dbUniversity.setRegion(x.getRegion());

        if (!x.getUniversityImage().isEmpty()) {
            String imagePath = storageService.store(x.getUniversityImage());
            dbUniversity.setUniversityImg(imagePath);
        }

        universityRepo.save(dbUniversity);

        return new RedirectView("/admin/universities/" + pageNumber);
    }
}