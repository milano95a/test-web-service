package home.controller.view;

import home.constant.UzLang;
import home.constant.Links;
import home.controller.BaseController;
import home.entity.*;
import home.model.Filter;
import home.utils.Page;
import home.model.Search;
import home.model.view.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;
import static home.constant.Constants.*;

@Controller
@RequestMapping("/admin")
public class AdminViewController extends BaseController {

    @GetMapping("/questions/{pageNumber}")
    String questionPage(Model model, @PathVariable int pageNumber){
        Page<QuestionView,Question> page = new Page<>(pageNumber,questionService,topicRepo,subjectRepo);
        page = page.getNextPage();
        model = setModelAttributes(model,page);
        return "template";
    }

    @PostMapping("/question/search")
    String questionSearchPage(Model model,@ModelAttribute Search search){
        Page<QuestionView,Question> page = new Page<>(questionService,topicRepo,subjectRepo);
        page.getSearchPage(search.getSearchText());
        model = setModelAttributes(model,page);
        return "template";
    }

    @PostMapping("/question/filter")
    String questionFilter(Model model, @ModelAttribute Filter filter){
        Page<QuestionView,Question> page = new Page<>(questionService,topicRepo,subjectRepo);
        page.getFilterPage(filter);
        model = setModelAttributes(model,page);
        return "template";
    }

    private Model setModelAttributes(Model model, Page page){
        List<String> subjects = getSubjectNames();
        List<String> languages = getLanguages();

        Links links = new Links();
        links.setContent(TABLE_QUESTION);
        links.setPagination(PAGINATION_QUESTION);
        links.setEditable(EDITABLE_QUESTION);
        links.setForm_filter(QUESTION_SEARCH_LINK);
        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_QUESTIONS);

        model.addAttribute("object", new Search());
        model.addAttribute("filter", new Filter());
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("page",page);
        model.addAttribute("username",getAdminName());
        model.addAttribute("languages", languages);
        model.addAttribute("subjects", subjects);

        return model;
    }

    @GetMapping("/courses/{pageNumber}")
    String getCoursesByPage(Model model, @PathVariable int pageNumber){
        Page<CourseView, Course> page = new Page<>(pageNumber,courseService);
        page.getNextPage();

        Links links = new Links();

        links.setContent(TABLE_COURSES);
        links.setPagination(PAGINATION_COURSES);
        links.setEditable(EDITABLE_COURSES);
        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_COURSES);
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("page",page);
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @GetMapping("/faculties/{pageNumber}")
    String getFacultiesByPage(Model model, @PathVariable int pageNumber){
        Page<FacultyView, Faculty> page = new Page<>(pageNumber,facultyService);
        page.getNextPage();

        Links links = new Links();
        links.setContent(TABLE_FACULTIES);
        links.setPagination(PAGINATION_FACULTIES);
        links.setEditable(EDITABLE_FACULTIES);
        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_FACULTIES);
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("page",page);
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @GetMapping("/topics/{pageNumber}")
    String getTopicsByPage(Model model, @PathVariable int pageNumber){
        Page<TopicView, Topic> page = new Page<>(pageNumber,topicService,questionRepo);
        page.getNextPage();

        Links links = new Links();
        links.setContent(TABLE_TOPICS);
        links.setPagination(PAGINATION_TOPICS);
        links.setEditable(EDITABLE_TOPICS);
        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_TOPICS);
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("page",page);
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @GetMapping("/universities/{pageNumber}")
    String getUniversity(Model model, @PathVariable int pageNumber){
        Page<UniversityView, University> page = new Page<>(pageNumber,universityService);
        page.getNextPage();

        Links links = new Links();

        links.setContent(TABLE_UNIVERSITIES);
        links.setPagination(PAGINATION_UNIVERSITIES);
        links.setEditable(EDITABLE_UNIVERSITIES);

        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_UNIVERSITIES);

        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("page",page);
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @GetMapping("/users/{pageNumber}")
    String getUser(Model model, @PathVariable int pageNumber){
        Page<UserView, User> page = new Page<>(pageNumber,userService);
        page.getNextPage();

        Links links = new Links();
        links.setContent(TABLE_USERS);
        links.setPagination(PAGINATION_USERS);
        links.setEditable(EDITABLE_USERS);
        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_USERS);
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("page",page);
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @GetMapping("/faqs/{pageNumber}")
    String getQAByPageNumber(Model model, @PathVariable int pageNumber){
        Page<QAView, QuestionQA> page = new Page<>(pageNumber,qaService);
        page.getNextPage();

        Links links = new Links();
        links.setContent(TABLE_FAQ);
        links.setPagination(PAGINATION_FAQ);
        links.setEditable(EDITABLE_FAQ);
        UzLang uzLang = new UzLang();
        uzLang.setContentTitle(UzLang.TABLE_FAQS);
        model.addAttribute("lang", uzLang);
        model.addAttribute("links", links);
        model.addAttribute("page",page);
        model.addAttribute("username",getAdminName());
        return "template";
    }

    @GetMapping("/questions/first")
    RedirectView getFirstPageOfQuestions(){
        return new RedirectView("/admin/questions/1");
    }

    @GetMapping("/questions/last")
    RedirectView getLastPageOfQuestions(){
        long count = questionRepo.count();
        long lastPage = count / 10;
        if((count % 10) > 1){
            lastPage++;
        }
        return new RedirectView("/admin/questions/" +lastPage);
    }

    @GetMapping("/courses/first")
    RedirectView getFirstPageOfCourses(){
        return new RedirectView("/admin/courses/1");
    }

    @GetMapping("/courses/last")
    RedirectView getLastPageOfCourses(){
        long count = courseRepo.count();
        long lastPage = count / 10;
        if((count % 10) > 1){
            lastPage++;
        }
        return new RedirectView("/admin/courses/" +lastPage);
    }

    @GetMapping("/faculties/first")
    RedirectView getFirstPageOfFaculties(){
        return new RedirectView("/admin/faculties/1");
    }

    @GetMapping("/faculties/last")
    RedirectView getLastPageOfFaculties(){
        long count = facultyRepo.count();
        long lastPage = count / 10;
        if((count % 10) > 1){
            lastPage++;
        }
        return new RedirectView("/admin/faculties/" +lastPage);
    }

    @GetMapping("/topics/first")
    RedirectView getFirstPageOfTopics(){
        return new RedirectView("/admin/topics/1");
    }

    @GetMapping("/topics/last")
    RedirectView getLastPageOfTopics(){
        long count = topicRepo.count();
        long lastPage = count / 10;
        if((count % 10) > 1){
            lastPage++;
        }
        return new RedirectView("/admin/topics/" +lastPage);
    }

    @GetMapping("/universities/first")
    RedirectView getFirstPageOfUniversities(){
        return new RedirectView("/admin/universities/1");
    }

    @GetMapping("/universities/last")
    RedirectView getLastPageOfUniversities(){
        long count = universityRepo.count();
        long lastPage = count / 10;
        if((count % 10) > 1){
            lastPage++;
        }
        return new RedirectView("/admin/universities/" +lastPage);
    }

    @GetMapping("/users/first")
    RedirectView getFirstPageOfUsers(){
        return new RedirectView("/admin/users/1");
    }

    @GetMapping("/users/last")
    RedirectView getLastPageOfUsers(){
        long count = userRepo.count();
        long lastPage = count / 10;
        if((count % 10) > 1){
            lastPage++;
        }
        return new RedirectView("/admin/users/" +lastPage);
    }

    @GetMapping("/faqs/first")
    RedirectView getFirstPageOfFaqs(){
        return new RedirectView("/admin/faqs/1");
    }

    @GetMapping("/faqs/last")
    RedirectView getLastPageOfFaqs(){
        long count = questionQARepo.count();
        long lastPage = count / 10;
        if((count % 10) > 1){
            lastPage++;
        }
        return new RedirectView("/admin/faqs/" +lastPage);
    }

//    private PageI getPage(int pageNumber)  {
//        List<Question> dbQuestions = new ArrayList<>();
//        int questionCount = questionRepo.countByQuestionIdGreaterThan(0);
//
//        if(pageNumber == 1){
//            dbQuestions = questionService.getPageQuestions(0);
//        }else{
//            if(pageNumber * PAGE_COUNT < questionCount){
//                int questionId = getQuestionIdOfStartPage(pageNumber);
//                dbQuestions = questionService.getPageQuestions(questionId);
//            }
//        }
//
//        PageI page = new PageI();
//        page.setCurrentpage(pageNumber);
//
//        if(pageNumber > 1){
//            page.setHasprevious(true);
//        }else{
//            page.setHasprevious(false);
//        }
//
//        if(questionCount > pageNumber * PAGE_COUNT){
//            page.setHasnext(true);
//        }else{
//            page.setHasnext(false);
//        }
//
//        List<Integer> pages = getPageNumbers(pageNumber,questionCount);
//
//        page.setPages(pages);
//
////        Specific
//        List<QuestionView> viewQuestions = new ArrayList<>();
//        for(Question q: dbQuestions){
//            Topic topic = topicRepo.findOne(q.getTopic().getTopicId());
//            Subject subject = subjectRepo.findOne(topic.getSubject().getSubjectId());
//            viewQuestions.add(new QuestionView(q,subject,topic));
//        }
//
//        page.setQuestions(viewQuestions);
//        return page;
//    }

//    private PageTopic getPageTopic(int pageNumber){
//
//        List<Topic> dbTopic = new ArrayList<>();
//        int count = topicRepo.countByTopicIdGreaterThan(0);
//
//        if(pageNumber == 1){
//            dbTopic = topicRepo.findTop10ByTopicIdGreaterThan(0);
//        }else{
//            if(pageNumber * PAGE_COUNT < count){
//                int topicId = getTopicIdOfStartPage(pageNumber);
//                dbTopic = topicRepo.findTop10ByTopicIdGreaterThan(topicId);
//            }
//        }
//
//        PageTopic page = new PageTopic();
//        page.setCurrentpage(pageNumber);
//
//        if(pageNumber > 1){
//            page.setHasprevious(true);
//        }else{
//            page.setHasprevious(false);
//        }
//
//        if(count > pageNumber * PAGE_COUNT){
//            page.setHasnext(true);
//        }else{
//            page.setHasnext(false);
//        }
//
//        List<Integer> pages = getPageNumbers(pageNumber,count);
//
//        page.setPages(pages);
//
//        List<TopicView> viewQuestions = new ArrayList<>();
//        for(Topic t: dbTopic){
//            TopicView topicView = new TopicView(t);
//            viewQuestions.add(topicView);
//        }
//
//        page.setList(viewQuestions);
//        return page;
//    }

//    private int getQuestionIdOfStartPage(int pageNumber){
//
//        int questionId = 0;
//
//        for(int i = 1; i < pageNumber; i++){
//            List<Question> questions = questionService.getPageQuestions(questionId);
//            int size = questions.size();
//            Question question = questions.get(size - 1);
//            questionId = question.getQuestionId();
//        }
//
//        return questionId;
//    }

//    private int getTopicIdOfStartPage(int pageNumber){
//
//        int topicId = 0;
//
//        for(int i = 1; i < pageNumber; i++){
//            List<Topic> topics = topicRepo.findTop10ByTopicIdGreaterThan(1);
//            int size = topics.size();
//            Topic topic = topics.get(size - 1);
//            topicId = topic.getTopicId();
//        }
//
//        return topicId;
//    }

//    private List<Integer> getPageNumbers(int currentPageNumber, int numberOfQuestions){
//        List<Integer> pageNumbers = new ArrayList<>();
//
//        int lastPage = numberOfQuestions / PAGE_COUNT;
//
//        if(numberOfQuestions % PAGE_COUNT > 0){
//            lastPage++;
//        }
//
//        if(lastPage < 6 ){
//            for(int i = 1; i < lastPage; i++) {
//                pageNumbers.add(i);
//            }
//        } else if(currentPageNumber - 2 <= 0){
//            for(int i = 1; i < 6; i++) {
//                pageNumbers.add(i);
//            }
//        }else if(currentPageNumber + 2 <= lastPage){
//            pageNumbers.add(currentPageNumber - 2);
//            pageNumbers.add(currentPageNumber - 1);
//            pageNumbers.add(currentPageNumber);
//            pageNumbers.add(currentPageNumber + 1);
//            pageNumbers.add(currentPageNumber + 2);
//        }else{
//            pageNumbers.add(lastPage - 4);
//            pageNumbers.add(lastPage - 3);
//            pageNumbers.add(lastPage - 2);
//            pageNumbers.add(lastPage - 1);
//            pageNumbers.add(lastPage);
//        }
//
//        return pageNumbers;
//    }
}
