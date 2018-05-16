package home.controller;

import home.entity.*;
import home.model.HistoryType;
import home.model.SubjectType;
import home.redis.RedisQuestion;
import home.redis.RedisRepo;
import home.security.domain.LoginType;
import home.security.domain.Token;
import home.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.PostConstruct;
import java.util.*;
import static home.constant.Constants.*;

@RestController
public class DeveloperController extends BaseController{

    @Autowired
    Environment env;

    @Autowired
    EmailUtil emailUtil;

    @PostConstruct
    public void init(){

        Arrays.stream(env.getActiveProfiles()).forEach(s -> {
            if(s.equals("dev") ){
                if(adminRepo.findAll().size() > 0){
                    DB_STATE = POPULATED;
                }

                addSubjects();

                if(subjectRepo.count() < 11){
                    addMissingSubjects();
                }

                populate();
            }
        });
    }

    private @ResponseBody Object populate(){
        if(DB_STATE == EMPTY){

////  region Add Admin
//            Admin admin = new Admin();
//            admin.setUsername("x@mail.com");
//            admin.setPassword(SHA.hash("123"));
//            adminRepo.save(admin);
////            endregion

////           region Add University
            for (int i = 0; i < 10; i++){
                University university = new University();
                university.setName("University " + i);
                university.setRuName("Университет "  +i);
                university.setUniversityPhone("+71 256 44 45");
                university.setLongitude(i + "");
                university.setLatitude(i + "");
                universityRepo.save(university);
            }
////          endregion

            List<University> universities = universityRepo.findAll();
            List<Subject> subjects = subjectRepo.findAll();

//            region Add Faculty
            for(int i = 0; i < universities.size(); i++){
                while(new Random().nextBoolean()){
                    Faculty faculty = new Faculty();
                    faculty.setName("Faculty " + i);
                    faculty.setRuName("Faculty ru " + i);
                    faculty.setContractPlace(10);
                    faculty.setContractScore(10.0);
                    faculty.setGrantPlace(40);
                    faculty.setGrantScore(12.0);
                    faculty.setUniversity(universities.get(i));
                    faculty.setSubjectTop(subjects.get(0));
                    faculty.setSubjectMid(subjects.get(1));
                    faculty.setSubjectBot(subjects.get(2));
                    facultyRepo.save(faculty);
                }
            }


//            for(int i = 0; i < universities.size(); i ++){
//                for(int k = 0; k < 5; k++){
//                    System.out.println(i + " Faculty " + k);
//                    Faculty faculty = new Faculty();
//                    faculty.setName("Faculty " + k);
//                    faculty.setRuName("Faculty ru " + k);
//                    faculty.setContractPlace(100);
//                    faculty.setContractScore(120.0);
//                    faculty.setGrantPlace(40);
//                    faculty.setGrantScore(170.9);
//                    faculty.setUniversity(universities.get(i));
//                    faculty.setSubjectTop(subjects.get(r(8)));
//                    faculty.setSubjectMid(subjects.get(r(8)));
//                    faculty.setSubjectBot(subjects.get(r(8)));
//                    facultyRepo.save(faculty);
//                }
//            }
//            endregion

//            region Add Topic
            for(int i = 0; i < subjects.size(); i++){
                for(int k = 0; k < 10; k++){
                    Topic topic = new Topic();
                    topic.setSubject(subjects.get(i));
                    topic.setTopic(subjects.get(i).getSubject() + " Topic " + i + "" + k);
                    topic.setRuTopic("Тема " + i + "" + k);
                    topic.setQuestionConfig(3);
                    topicRepo.save(topic);
                }
            }
//            endregion
            List<Topic> topics = topicRepo.findAll();

////            region Add Question
            for(int i = 0; i < topics.size(); i++){
                List<Question> questions = new ArrayList<>();
                List<Answer> answers = new ArrayList<>();
                if(new Random().nextBoolean()){
                    for(int k = 0; k < 1; k++){
                        System.out.println(i + " Question /" + k);
                        Question question = new Question();
                        question.setTopic(topics.get(i));
                        question.setQuestionText(i + "" + k + "Question");
                        question.setLang(UZ);
                        if(k < 5) {
                            question.setQuestionImg("https://i.stack.imgur.com/ezFHM.png");
                        }
                        question.setDescription(getSaltString());
                        questions.add(question);
                        //                    questionRepo.save(question);

                        Answer answer1 = new Answer();
                        answer1.setQuestion(question);
                        answer1.setAnswer(1 + " Contrary to popular belief, Lorem Ipsum is not simply random text.");
                        answer1.setStatus(false);
                        //                    answer1.setAnswerImg("https://i.stack.imgur.com/ezFHM.png");
                        answers.add(answer1);

                        Answer answer2 = new Answer();
                        answer2.setQuestion(question);
                        answer2.setAnswer(2 + " Contrary to popular belief, Lorem Ipsum is not simply random text.");
                        answer2.setStatus(false);
                        //                    answer2.setAnswerImg("https://i.stack.imgur.com/ezFHM.png");
                        answers.add(answer2);

                        Answer answer3 = new Answer();
                        answer3.setQuestion(question);
                        answer3.setAnswer(3 + " Contrary to popular belief, Lorem Ipsum is not simply random text.");
                        answer3.setStatus(true);
                        //                    answer3.setAnswerImg("https://i.stack.imgur.com/ezFHM.png");
                        answers.add(answer3);

                        Answer answer4 = new Answer();
                        answer4.setQuestion(question);
                        answer4.setAnswer(4 + " Contrary to popular belief, Lorem Ipsum is not simply random text.");
                        answer4.setStatus(false);
                        //                    answer4.setAnswerImg("https://i.stack.imgur.com/ezFHM.png");
                        answers.add(answer4);
                    }
                }

                questionRepo.save(questions);
                answerRepo.save(answers);
            }
//            endregion

////            region Add ru Question
//            for(int i = 1; i < topics.size(); i++){
//
//                List<Question> questions = new ArrayList<>();
//                List<Answer> answers = new ArrayList<>();
//
//                for(int k = 0; k < 40; k++){
//                    System.out.println(i + " Ru Question " + k);
//                    Question question = new Question();
//                    question.setTopic(topics.get(i));
//                    question.setQuestionText(i + "" + k + " Он помогает выделить визуальные элементы в документе или презентации, например текст, шрифт или разметка.");
//                    question.setLang(RUS);
//                    questions.add(question);
////                    questionRepo.save(question);
//
//                    Answer answer1 = new Answer();
//                    answer1.setQuestion(question);
//                    answer1.setAnswer(1 + " Это очень удобный инструмент для моделей макетов.");
//                    answer1.setStatus(false);
//                    answers.add(answer1);
////                    answerRepo.save(answer1);
//
//                    Answer answer2 = new Answer();
//                    answer2.setQuestion(question);
//                    answer2.setAnswer(2 + " Это очень удобный инструмент для моделей макетов.");
//                    answer2.setStatus(false);
//                    answers.add(answer2);
////                    answerRepo.save(answer2);
//
//                    Answer answer3 = new Answer();
//                    answer3.setQuestion(question);
//                    answer3.setAnswer(3 + " Это очень удобный инструмент для моделей макетов.");
//                    answer3.setStatus(true);
//                    answers.add(answer3);
////                    answerRepo.save(answer3);
//
//                    Answer answer4 = new Answer();
//                    answer4.setQuestion(question);
//                    answer4.setAnswer(4 + " Это очень удобный инструмент для моделей макетов.");
//                    answer4.setStatus(false);
//                    answers.add(answer4);
////                    answerRepo.save(answer4);
//                }
//                questionRepo.save(questions);
//                answerRepo.save(answers);
//            }
////            endregion

            User u = null;

////            region Add User
            for(int i = 0; i < 20; i++){
                User user = new User();
                String userId = "milano9" + i + "a@gmail.com";
                user.setUserId(userId);
                user.setUsername("Username " + i);
                user.setLoginType(LoginType.Email);
                user.setCreateDate(new Date());
                user.setPassword("Blabla");
                user.setScore(i);
                u = user;
                userRepo.save(user);

                Token token = new Token();
                token.setUserId(userId);
                token.setToken(i + "123456789");
                tokenRepo.save(token);
            }
//
////            endregion

//  region
            for(Question q: questionRepo.findAll()){
                History history = new History();
                history.setQuestion(q);
                history.setSubject(subjects.get(0));
                history.setDate(new Date().getTime());
                history.setMyAnswer(q.getAnswers().get(0));
                history.setHistoryType(HistoryType.Exam);
                history.setSessionId(new Random().nextInt());
                history.setUser(u);
                historyRepo.save(history);
            }
//  endregion

////region Courses
            List<Course> courses = new ArrayList<>();
            for(int i = 0; i < 25; i++){
                Course course = new Course();
                course.setCourseName("Course Name " + i);
                course.setLatitude("latitude");
                course.setLongitude("longitude");
                course.setCoursePhone("Phone");
                courses.add(course);
            }
            courseRepo.save(courses);
////endregion

////region QA
            List<QuestionQA> questionQAS= new ArrayList<>();
            for(int i = 0; i < 15; i++){
                AnswerQA answerQA = new AnswerQA("Answer " +i,"Russian Answer "+i);
                answerQARepo.save(answerQA);

                QuestionQA questionQA = new QuestionQA();
                questionQA.setQuestionText("Question " + i);
                questionQA.setRuquestionText("Russian Question " +i);
                questionQA.setAnswer(answerQA);
                questionQAS.add(questionQA);
            }
            questionQARepo.save(questionQAS);
////endregion

//            endregion

            DB_STATE = POPULATED;
            return "db populated";
        }

        return "db already populated";
    }

//    region All

    @Autowired
    RedisRepo redisRepo;

    @GetMapping(path = {"/docs"})
    public RedirectView home(){
        return new RedirectView("/swagger-ui.html");
    }

    @GetMapping(path = {"/", ""})
    public RedirectView adminHome(){
        return new RedirectView("/admin/questions/1");
    }


    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @GetMapping(path = "/{lang}/topicsBy/{topicId}")
    @ResponseBody Object t3(@PathVariable int lang, @PathVariable int topicId){
        return questionRepo.getQuestionsByLangAndTopic_TopicId(lang, topicId);
    }

    @GetMapping("/allUsers")
    @ResponseBody Object getUser() {
        return userRepo.findAll();
    }

    @GetMapping("/allTokens")
    @ResponseBody Object getTokens() {
        return tokenRepo.findAll();
    }

    @GetMapping("/allQuestions")
    @ResponseBody Object getAllQuestions() {
        ArrayList ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);

        return questionRepo.getNRandomQuestions(1,ids,10);
    }

    @GetMapping("/question/{id}")
    @ResponseBody Object getAllQuestions(@PathVariable int id) {
        return questionRepo.findOne(id);
    }

    @GetMapping("/question/subject/{id}")
    @ResponseBody Object getAllQuestionBySubject(@PathVariable int id) {
        return questionRepo.findQuestionsByTopic_Subject_SubjectIdAndLang(id,UZ);
    }

    @GetMapping("/allConfirmationCode")
    @ResponseBody Object getConfirmationCode() {
        return confirmationCodeRepo.findAll();
    }

    @GetMapping("/errors")
    @ResponseBody Object getErrors(){
        return exRepo.findAll();
    }

    @GetMapping("/errorById/{id}")
    @ResponseBody Object getError(@PathVariable Integer id){
        return exRepo.findOne(id);
    }

    @GetMapping("/errorDelete/{id}")
    @ResponseBody Object deleteErr(@PathVariable Integer id){
        exRepo.delete(id);
        return exRepo.findAll();
    }

    @GetMapping("/send/{mail}")
    @ResponseBody Object sendMail(@PathVariable String mail){
        try{
            emailUtil.sendConfirmationCode("milano95j@gmail.com",1111);
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
        return "send";
    }

    @GetMapping("/allSubjects")
    @ResponseBody Object getSubjects(){
        return subjectRepo.findAll();
    }


    @GetMapping("/topics/{subjectId}")
    @ResponseBody Object getTopics(@PathVariable int subjectId) {
        return topicService.findTopicsBySubjectId(subjectId);
    }

    @GetMapping("/cache/{subject}")
    @ResponseBody Object cache(@PathVariable String subject){
        return redisRepo.findByKey(subject);
    }

    @GetMapping("/cache/all")
    @ResponseBody Object cacheAll(){
        return redisRepo.findAll();
    }

    @GetMapping("/cache/delete")
    @ResponseBody Object deleteAll(){
        redisRepo.deleteAll();
        return redisRepo.findAll();
    }


    @GetMapping("/pop")
    @ResponseBody Object pop(){
        try{
            List<Question> questions = questionService.makeExamQuestions(1,1);

            List<RedisQuestion> redisQuestions = new ArrayList<>();

            for(Question question: questions){
                redisQuestions.add(new RedisQuestion(question));
            }
            redisRepo.save("obj",redisQuestions);
            return redisQuestions;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("history")
    @ResponseBody Object history(){
        return historyRepo.findAll();
    }

    @GetMapping("/leaderboard")
    @ResponseBody Object leaderboard() {
        return getLederboard();
    }

    private void addSubjects(){
        //            region Add Subject
        Subject math = new Subject();
        math.setSubject("Matematika");
        math.setRuSubject("Математика");
        math.setType(SubjectType.Exact);
        subjectRepo.save(math);

        Subject physics = new Subject();
        physics.setSubject("Fizika");
        physics.setRuSubject("Физика");
        physics.setType(SubjectType.Exact);
        subjectRepo.save(physics);

        Subject biology = new Subject();
        biology.setSubject("Biologiya");
        biology.setRuSubject("Биология");
        biology.setType(SubjectType.Natural);
        subjectRepo.save(biology);

        Subject chemistry = new Subject();
        chemistry.setSubject("Kimyo");
        chemistry.setRuSubject("Химия");
        chemistry.setType(SubjectType.Natural);
        subjectRepo.save(chemistry);

        Subject geography = new Subject();
        geography.setSubject("Geografiya");
        geography.setRuSubject("География");
        geography.setType(SubjectType.Natural);
        subjectRepo.save(geography);

        Subject history = new Subject();
        history.setSubject("Tarix");
        history.setRuSubject("История");
        history.setType(SubjectType.Humanitarian);
        subjectRepo.save(history);

        Subject uzbek = new Subject();
        uzbek.setSubject("Ona tili");
        uzbek.setRuSubject("Узбекский");
        uzbek.setType(SubjectType.Humanitarian);
        subjectRepo.save(uzbek);

        Subject russian = new Subject();
        russian.setSubject("Rus tili");
        russian.setRuSubject("Русский");
        russian.setType(SubjectType.Humanitarian);
        subjectRepo.save(russian);

        Subject english = new Subject();
        english.setSubject("Ingliz tili");
        english.setRuSubject("Английский");
        english.setType(SubjectType.Humanitarian);
        subjectRepo.save(english);

//            endregion
    }

    private void addMissingSubjects(){

        Subject geometry = new Subject();
        geometry.setSubject("Geometriya");
        geometry.setRuSubject("Геометрия");
        geometry.setType(SubjectType.Exact);
        subjectRepo.save(geometry);

        Subject literature = new Subject();
        literature.setSubject("Adabiyot");
        literature.setRuSubject("Литература");
        literature.setType(SubjectType.Humanitarian);
        subjectRepo.save(literature);

        Subject uzbekHistory = new Subject();
        uzbekHistory.setSubject("O'zbekiston tarixi");
        uzbekHistory.setRuSubject("История Узбекистана");
        uzbekHistory.setType(SubjectType.Humanitarian);
        subjectRepo.save(uzbekHistory);
    }

//    endregion

}
