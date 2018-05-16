package home.utils;

import home.repo.*;
import home.security.repository.IAdminRepo;
import home.security.repository.IConfirmationCodeRepo;
import home.security.repository.ITokenRepo;
import home.service.*;
import home.uploader.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

public class Repos {

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
}
