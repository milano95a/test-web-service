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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static home.constant.Constants.*;

@Controller
@RequestMapping("/admin")
public class AdminAddController extends BaseController {

    @GetMapping("/question_form")
    String getQuestionForm(Model model){

        List<String> subjects = getSubjectNames();
        List<String> languages = getLanguages();

        Links links = new Links();
        links.setContent(QUESTION_FORM_CONTENT);
        links.setForm_submit(QUESTION_FORM_ADD);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.FORM_QUESTIONS);
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("languages", languages);
        model.addAttribute("subjects", subjects);
        model.addAttribute("submit", "submit");
        model.addAttribute("object", new QuestionView());
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @PostMapping("/question_form")
    RedirectView postQuestionForm(@ModelAttribute QuestionView q){
        Question question = convertQuestionViewToQuestionModel(q);
        questionRepo.save(question);

//
//        if(q.getLanguage().contains(RUS_STRING)){
//            question.setLang(RUS);
//        }else{
//            question.setLang(UZ);
//        }
//
//        Topic topic = topicService.getTopicByTopicName(q.getTopic());
//
//        question.setTopic(topic);
//
//        if(!q.getQuestionImg().isEmpty()){
//            String path = storageService.store(q.getQuestionImg());
//            question.setQuestionImg(path);
//        }
//
//        if(q.getQuestionText() != null){
//            question.setQuestionText(q.getQuestionText());
//        }
//
//
//        if(q.getDescImg().isEmpty()){
//            question.setDescription(q.getDescText());
//        }else{
//            String path = storageService.store(q.getDescImg());
//            question.setDescriptionImg(path);
//        }


//        List<Answer> answers = new ArrayList<>();

        Answer answerA = convertAnswerViewToAnswerModel(q.getAText(),
                q.getAImg(),
                q.getCorrect(),
                "a",
                question);
        answerRepo.save(answerA);

        Answer answerB = convertAnswerViewToAnswerModel(q.getBText(),
                q.getBImg(),
                q.getCorrect(),
                "b",
                question);
        answerRepo.save(answerB);

        Answer answerC = convertAnswerViewToAnswerModel(q.getCText(),
                q.getCImg(),
                q.getCorrect(),
                "c",
                question);
        answerRepo.save(answerC);

        Answer answerD = convertAnswerViewToAnswerModel(q.getDText(),
                q.getDImg(),
                q.getCorrect(),
                "d",
                question);
        answerRepo.save(answerD);


//        if(q.getAImg().isEmpty()){
//            answerA.setAnswer(q.getAText());
//        }else{
//            String path = storageService.store(q.getAImg());
//            answerA.setAnswerImg(path);
//        }
//
//        if(q.getCorrect().equals("a")){
//            answerA.setStatus(true);
//        }else{
//            answerA.setStatus(false);
//        }
//        answerA.setQuestion(question);
//        answerRepo.save(answerA);
//        answers.add(answerA);



//        Answer answerB = new Answer();
//        if(q.getBImg().isEmpty()){
//            answerB.setAnswer(q.getBText());
//        }else{
//            String path = storageService.store(q.getBImg());
//            answerB.setAnswerImg(path);
//        }
//        if(q.getCorrect().equals("b")){
//            answerB.setStatus(true);
//        }else{
//            answerB.setStatus(false);
//        }
//        answerB.setQuestion(question);
//        answerRepo.save(answerB);
//        answers.add(answerB);


//        Answer answerC = new Answer();
//        if(q.getCImg().isEmpty()){
//            answerC.setAnswer(q.getCText());
//        }else{
//            String path = storageService.store(q.getCImg());
//            answerC.setAnswerImg(path);
//        }
//        if(q.getCorrect().equals("c")){
//            answerC.setStatus(true);
//        }else{
//            answerC.setStatus(false);
//        }
//        answerC.setQuestion(question);
//        answerRepo.save(answerC);
//        answers.add(answerC);


//        Answer answerD = new Answer();
//        if(q.getDImg().isEmpty()){
//            answerD.setAnswer(q.getDText());
//        }else{
//            String path = storageService.store(q.getDImg());
//            answerD.setAnswerImg(path);
//        }
//        if(q.getCorrect().equals("d")){
//            answerD.setStatus(true);
//        }else{
//            answerD.setStatus(false);
//        }
//        answerD.setQuestion(question);
//        answerRepo.save(answerD);
//        answers.add(answerD);

        return new RedirectView("/admin/question_form");
    }

    private Question convertQuestionViewToQuestionModel(QuestionView q){
        Question question = new Question();

        if(q.getLanguage().contains(RUS_STRING)){
            question.setLang(RUS);
        }else{
            question.setLang(UZ);
        }

        question.setQuestionText(q.getQuestionText());
        question.setDescription(q.getDescText());

        if(!q.getQuestionImg().isEmpty()){
            String path = storageService.store(q.getQuestionImg());
            question.setQuestionImg(path);
        }else{
            question.setQuestionImg(null);
        }

        if(!q.getDescImg().isEmpty()){
            String path = storageService.store(q.getDescImg());
            question.setDescriptionImg(path);
        }else{
            question.setDescriptionImg(null);
        }

        Topic topic = topicService.getTopicByTopicName(q.getTopic());
        question.setTopic(topic);

        return question;
    }

    private Answer convertAnswerViewToAnswerModel(String answerText, MultipartFile image, String trueAnswer, String currentAnswer, Question question){
        Answer answer = new Answer();
        answer.setAnswer(answerText);

        if(!image.isEmpty()){
            String path = storageService.store(image);
            answer.setAnswerImg(path);
        }else{
            answer.setAnswerImg(null);
        }

        if (trueAnswer.equals(currentAnswer)) {
            answer.setStatus(true);
        } else {
            answer.setStatus(false);
        }

        answer.setQuestion(question);
        return answer;
    }

    @GetMapping("/topic_form")
    String getTopicForm(Model model){

        List<String> subjects = new ArrayList<>();

        for(Subject s: subjectRepo.findAll()){
            subjects.add(s.getSubject());
        }

        Links links = new Links();
        links.setContent(TOPIC_FORM_CONTENT);
        links.setForm_submit(TOPIC_FORM_ADD);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.FORM_TOPICS);
        model.addAttribute("lang", uzLang);

        model.addAttribute("links", links);
        model.addAttribute("submit", "submit");
        model.addAttribute("subjects", subjects);
//        model.addAttribute("configs", configs);
        model.addAttribute("object", new TopicView());
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @PostMapping("/topic_form")
    RedirectView postTopicForm(@ModelAttribute TopicView t){


        Topic topic = new Topic();

        Subject subject = subjectRepo.findTop1SubjectBySubjectOrRuSubject(t.getSubject(),t.getSubject());
        topic.setSubject(subject);

        if(!t.getTopic().equals("")){
            topic.setTopic(t.getTopic());
        }else{
            t.setTopic("None");
        }

        if(!t.getRuTopic().equals("")){
            topic.setRuTopic(t.getRuTopic());
        }else{
            t.setTopic("None");
        }

        topic.setQuestionConfig(3);

        topicRepo.save(topic);

        return new RedirectView("/admin/topic_form");
    }

    @GetMapping("/course_form")
    String getCourseForm(Model model){
        Links links = new Links();
        links.setContent(COURSE_FORM_CONTENT);
        links.setForm_submit(COURSE_FORM_ADD);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.FORM_COURSES);
        model.addAttribute("lang", uzLang);

        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("object", new CourseView());
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @PostMapping("/course_form")
    RedirectView postCourseForm(@ModelAttribute CourseView c){

        if(!c.courseImg.isEmpty()){
            String path = storageService.store(c.courseImg);
            Course course = new Course(c,path);
            courseRepo.save(course);
        }else{
            Course course = new Course(c);
            courseRepo.save(course);
        }

        return new RedirectView("/admin/course_form");
    }

    @GetMapping("/university_form")
    String getUniversityForm(Model model){
        Links links = new Links();
        links.setContent(UNIVERSITY_FORM_CONTENT);
        links.setForm_submit(UNIVERSITY_FORM_ADD);

        model.addAttribute("links", links);
        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.FORM_UNIVERSITIES);
        model.addAttribute("lang", uzLang);
        model.addAttribute("region",getRegions());
        model.addAttribute("submit", "submit");
        model.addAttribute("object", new UniversityView());
        model.addAttribute("username",getAdminName());
        return "template";
    }


    @PostMapping("/university_form")
    RedirectView postUniversityForm(@ModelAttribute UniversityView universityView){

        universityView.setLatitude("");
        universityView.setLongitude("");

        if(!universityView.getUniversityImage().isEmpty()){
            String path = storageService.store(universityView.getUniversityImage());
            University university = new University(universityView,path);
            universityRepo.save(university);

        }else{
            University university = new University(universityView);
            universityRepo.save(university);
        }

        return new RedirectView("/admin/university_form");
    }

    @GetMapping("/faq_form")
    String getQAForm(Model model){
        Links links = new Links();
        links.setForm_submit(FAQ_FORM_ADD);
        links.setContent(FAQ_FORM_CONTENT);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.FORM_FAQS);
        model.addAttribute("lang", uzLang);

        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("object", new QAView());
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @PostMapping("/faq_form")
    RedirectView postQAForm(@ModelAttribute QAView q){

        QuestionQA questionQA = new QuestionQA();
        AnswerQA answerQA = new AnswerQA();

        if(!q.getQuestionText().equals("")){
            questionQA.setQuestionText(q.getQuestionText());
        }else{
            questionQA.setQuestionText("None");
        }

        if(!q.getRuquestionText().equals("")){
            questionQA.setRuquestionText(q.getRuquestionText());
        }else{
            questionQA.setRuquestionText("None");
        }

        if(!q.getAnswer().equals("")){
            answerQA.setAnswer(q.getAnswer());
        }else{
            answerQA.setAnswer("None");
        }

        if(!q.getRuanswer().equals("")){
            answerQA.setRuanswer(q.getRuanswer());
        }else{
            answerQA.setRuanswer("None");
        }

        answerQARepo.save(answerQA);
        questionQA.setAnswer(answerQA);
        questionQARepo.save(questionQA);

        return new RedirectView("/admin/faq_form");
    }

    @GetMapping("/faculty_form")
    String getFacultyForm(Model model){

        Links links = new Links();
        links.setForm_submit(FACULTY_FORM_ADD);
        links.setContent(FACULTY_FORM_CONTENT);

        links.setForm_submit("/admin/faculty_form");

        List<University> universityList = universityRepo.findAll();
        List<String> universities = new ArrayList<>();
        for(University u: universityList){
            universities.add(u.getName());
        }

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.FORM_FACULTIES);
        model.addAttribute("lang", uzLang);
        model.addAttribute("submit", "submit");
        model.addAttribute("links", links);
        model.addAttribute("unis", universities);
        model.addAttribute("subjects", getSubjectsForFaculty());
        model.addAttribute("object", new FacultyView());
        model.addAttribute("username",getAdminName());
        return "template";
    }

    private Set<String> getSubjectsForFaculty(){
        List<Subject> subjectList = subjectRepo.findAll();

        Set<String> subjects = new LinkedHashSet<>();
        for(Subject s: subjectList){
            if(s.getSubject().equals("Tarix") || s.getSubject().equals("O'zbekiston tarixi")){
                subjects.add("Tarix");
            }else if(s.getSubject().equals("Adabiyot") || s.getSubject().equals("Ona tili")){
                subjects.add("Ona tili va Adabiyot");
            }else if(s.getSubject().equals("Matematika") || s.getSubject().equals("Geometriya")){
                subjects.add("Matematika");
            }else{
                subjects.add(s.getSubject());
            }
        }

        return subjects;
    }

    @PostMapping("/faculty_form")
    RedirectView postFacultyForm(@ModelAttribute FacultyView facultyView){

        University university = universityRepo.findUniversityByName(facultyView.getUniversityName());
        if(facultyView.getTopSubject().contains("Ona tili va ")){
            facultyView.setTopSubject("Ona tili");
        }
        if(facultyView.getMidSubject().contains("Ona tili va ")){
            facultyView.setMidSubject("Ona tili");
        }
        if(facultyView.getBotSubject().contains("Ona tili va ")){
            facultyView.setBotSubject("Ona tili");
        }

        Subject topSubject = subjectRepo.findTop1SubjectBySubjectOrRuSubject(facultyView.getTopSubject(),facultyView.getTopSubject());
        Subject midSubject = subjectRepo.findTop1SubjectBySubjectOrRuSubject(facultyView.getMidSubject(),facultyView.getMidSubject());
        Subject botSubject = subjectRepo.findTop1SubjectBySubjectOrRuSubject(facultyView.getBotSubject(),facultyView.getBotSubject());

        Faculty faculty = new Faculty(facultyView,university,topSubject,midSubject,botSubject);
        facultyRepo.save(faculty);

        return new RedirectView("/admin/faculty_form");
    }
}