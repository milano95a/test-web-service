package home.controller;

import home.entity.*;
import home.entity.User;
import home.exception.EntryNotFoundException;
import home.model.Exam;
import home.model.ExamHistory;
import home.model.UniversityResult;
import home.repo.*;
import home.security.repository.IAdminRepo;
import home.security.repository.IConfirmationCodeRepo;
import home.security.repository.ITokenRepo;
import home.service.*;
import home.uploader.storage.StorageService;
import home.utils.EmailUtil;
import home.utils.ExamEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static home.constant.Constants.NUMBER_OF_USERS_IN_LEADERBOARD;
import static home.constant.Constants.REGIONS;

public class BaseController {

    public User user(){

        if(SecurityContextHolder.getContext().getAuthentication()!= null) {
            return (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        }
        return null;

//        return userRepo.findOne("milano95a@gmail.com");
    }

//    region All

    @Autowired
    ExamEngine examEngine;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    IExRepo exRepo;

    @Autowired
    public IAdminRepo adminRepo;

    @Autowired
    public StorageService storageService;

    @Autowired
    public CourseService courseService;

    @Autowired
    public QAService qaService;

    @Autowired
    public UniversityService universityService;

    @Autowired
    public UserService userService;

    @Autowired
    public IUniversityRepo universityRepo;

    @Autowired
    public IAnswerRepo answerRepo;

    @Autowired
    public IFacultyRepo facultyRepo;

    @Autowired
    public ISubjectRepo subjectRepo;

    @Autowired
    public ITopicRepo topicRepo;

    @Autowired
    public IQuestionRepo questionRepo;

    @Autowired
    public QuestionService questionService;

    @Autowired
    public TopicService topicService;

    @Autowired
    public IHistoryRepo historyRepo;

    @Autowired
    public HistoryService historyService;

    @Autowired
    public IUserRepo userRepo;

    @Autowired
    public SubjectService subjectService;

    @Autowired
    public FacultyService facultyService;

    @Autowired
    public IQuestionQARepo questionQARepo;

    @Autowired
    public IAnswerQARepo answerQARepo;

    @Autowired
    public ICourseRepo courseRepo;

    @Autowired
    public ITokenRepo tokenRepo;

    @Autowired
    public IConfirmationCodeRepo confirmationCodeRepo;

    Object unauthorized(String message){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(makeMessage("unauthorized",message));
    }

    Object badRequest(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(makeMessage("bad request",message));
    }

    Object ok(Object body){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }

    Object ok(String title, String body){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(makeMessage(title,body));
    }

    HashMap<String,String> makeMessage(String title, String message){
        HashMap<String,String> m = new HashMap<>();
        m.put(title,message);
        return m;
    }

    String getToken(){
        return "ku0jn9qpcqqqn6i1j3bsqq9cvq";
    }

    HashMap<Integer, Integer> calculateCorrectAnswers(Exam exam, String userId){

        int topScore = 0;
        int midScore = 0;
        int botScore = 0;

        for(Question q: exam.getQuestionTop()){
            if(q.getSelectAnswer() != null) {
                if (q.getSelectAnswer().getStatus()) {
                    topScore++;
                }
            }
        }

        for(Question q: exam.getQuestionMid()){
            if(q.getSelectAnswer() != null){
                if(q.getSelectAnswer().getStatus()){
                    midScore++;
                }
            }
        }

        for(Question q: exam.getQuestionBot()){
            if(q.getSelectAnswer() != null){
                if(q.getSelectAnswer().getStatus()){
                    botScore++;
                }
            }
        }

        User user = userRepo.findOne(userId);
        user.incrementScore(topScore);
        user.incrementScore(midScore);
        user.incrementScore(botScore);
        userRepo.save(user);


        HashMap<Integer,Integer> subjectIdScore = new HashMap<>();
        subjectIdScore.put(exam.getSubjectTop().getSubjectId(),topScore);
        subjectIdScore.put(exam.getSubjectMid().getSubjectId(),midScore);
        subjectIdScore.put(exam.getSubjectBot().getSubjectId(),botScore);

        return subjectIdScore;
    }

    Object getMyUni(Exam exam, HashMap correct){
        try{
            int s1 = exam.getSubjectTop().getSubjectId();
            int s2 = exam.getSubjectMid().getSubjectId();
            int s3 = exam.getSubjectBot().getSubjectId();

            List<Faculty> faculties = facultyService.getFacultiesUserCanGetIn(s1,s2,s3, correct);
            Set<University> universities = universityRepo.getUniversitiesByFacultiesIn(faculties);

            List<UniversityResult> universityResults = new ArrayList<>();

            for(University u: universities){
                universityResults.add(new UniversityResult(u,faculties));
            }

            return universityResults;
        }catch (EntryNotFoundException e){
            return badRequest(e.getMessage());
        }
    }

    Object getMyUni(ExamHistory examHistory){
        try{
            int s1 = examHistory.getSubjectTop().getSubjectId();
            int s2 = examHistory.getSubjectMid().getSubjectId();
            int s3 = examHistory.getSubjectBot().getSubjectId();

            HashMap<Integer, Integer> score = new HashMap<>();

            score.put(s1,examHistory.getResultTop());
            score.put(s2,examHistory.getResultMid());
            score.put(s3,examHistory.getResultBot());

            List<Faculty> faculties = facultyService.getFacultiesUserCanGetIn(s1,s2,s3, score);
            Set<University> universities = universityRepo.getUniversitiesByFacultiesIn(faculties);

            List<UniversityResult> universityResults = new ArrayList<>();

            for(University u: universities){
                universityResults.add(new UniversityResult(u,faculties));
            }

            return universityResults;
        }catch (EntryNotFoundException e){
            return badRequest(e.getMessage());
        }
    }

    public List<String> getSubjectNames(){
        List<String> subjects = new ArrayList<>();
        for(Subject s: subjectRepo.findAll()){
            subjects.add(s.getSubject());
        }

        return subjects;
    }

    public List<String> getLanguages(){
        List<String> languages = new ArrayList<>();
        languages.add("Uzbek");
        languages.add("Russian");

        return languages;
    }

    public String getAdminName(){
        return "Admin";
    }

    public List<String> getRegions(){
        return REGIONS;
    }

    public Object getLederboard() {

        List<User> users = new ArrayList<>();
        List<User> usersByScore = userRepo.findAllByScoreGreaterThanEqualOrderByScoreDesc(0);
        User user = user();

        usersByScore.forEach(u -> {
            if (u.getUserId().equals(user.getUserId())) {
                users.add(u);
            } else {
                if (users.size() < NUMBER_OF_USERS_IN_LEADERBOARD)
                    users.add(u);
            }
        });

        return users;
    }

        //    endregion

}
