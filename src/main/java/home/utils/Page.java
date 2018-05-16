package home.utils;

import home.entity.*;
import home.model.Filter;
import home.model.view.*;
import home.repo.IQuestionRepo;
import home.repo.ISubjectRepo;
import home.repo.ITopicRepo;
import home.service.BaseService;
import home.service.QuestionService;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import static home.constant.Constants.*;

public @Data class Page<V, E> {

    private List<E> dbItemsOnRequestedPage;
    private List<V> list; // view item list

    private List<Integer> pages;
    private int currentpage;
    private boolean hasnext;
    private boolean hasprevious;

    private int requestedPage;
    private int totalItems;

    private BaseService repository;
    private QuestionService questionService;
    private ITopicRepo topicRepo;
    private ISubjectRepo subjectRepo;
    private IQuestionRepo questionRepo;

    boolean haslast = true;

    public String first(){
        return "first";
    }

    public String last(){
        return "last";
    }

    public Page(){}

    public Page(int requestedPage, BaseService repository){
        this.requestedPage = requestedPage;
        this.repository = repository;
        this.dbItemsOnRequestedPage = new ArrayList<>();
        this.totalItems = repository.count();
    }

    public Page(int requestedPage, BaseService repository, IQuestionRepo questionRepo){
        this.requestedPage = requestedPage;
        this.repository = repository;
        this.dbItemsOnRequestedPage = new ArrayList<>();
        this.totalItems = repository.count();
        this.questionRepo = questionRepo;
    }

    public Page(QuestionService questionService, ITopicRepo topicRepo, ISubjectRepo subjectRepo){
        this.questionService = questionService;
        this.dbItemsOnRequestedPage = new ArrayList<>();
        this.totalItems = questionService.count();
        this.topicRepo = topicRepo;
        this.subjectRepo = subjectRepo;
    }

    public Page(int requestedPage, QuestionService questionService, ITopicRepo topicRepo, ISubjectRepo subjectRepo){
        this.requestedPage = requestedPage;
        this.questionService= questionService;
        this.repository = questionService;
        this.dbItemsOnRequestedPage = new ArrayList<>();
        this.totalItems = questionService.count();
        this.topicRepo = topicRepo;
        this.subjectRepo = subjectRepo;
    }


    public Page getNextPage(){

        if(isRequestedPageIsFirstPage()){
            dbItemsOnRequestedPage = getItemsOnRequestedPageWith(0);
        }else{
            if(isPageExist()){
                int idOfFirstItemOnRequestedPage = getIdOfFirstItemOnRequestedPage();
                dbItemsOnRequestedPage = getItemsOnRequestedPageWith(idOfFirstItemOnRequestedPage);
            }
        }

        setCurrentpage(requestedPage);

        if(isPreviousPageExist()){
            setHasprevious(true);
        }else{
            setHasprevious(false);
        }

        if(isNextPageExist()){
            setHasnext(true);
        }else{
            setHasnext(false);
        }

        setPagination(getNextAndPreviousPageNumbers());
        setPageItems();
        return this;
    }

    public Page getSearchPage(String questionSearchText){

        dbItemsOnRequestedPage = questionService.findBySearchText(questionSearchText);

        setCurrentpage(1);
        setHasprevious(false);
        setHasnext(false);

        List<Integer> pages = new ArrayList<>();
        pages.add(1);

        setPages(pages);
        List<V> viewList = convertDBItemsToViewItems();
        setList(viewList);

        this.totalItems = dbItemsOnRequestedPage.size();
        return this;
    }

    public Page getFilterPage(Filter filter){

        if(filter.getLanguage().equals("Russian")){
            if(filter.getTopic().equals("")){
                dbItemsOnRequestedPage = getQuestionsBySubject(filter.getLanguage(),filter.getSubject());
            }else{
                dbItemsOnRequestedPage = getQuestionsByTopic(filter.getLanguage(),filter.getTopic());
            }
        }else{
            if(filter.getTopic().equals("")){
                dbItemsOnRequestedPage = getQuestionsBySubject(filter.getLanguage(),filter.getSubject());
            }else{
                dbItemsOnRequestedPage = getQuestionsByTopic(filter.getLanguage(),filter.getTopic());
            }
        }

        setCurrentpage(1);
        setHasprevious(false);
        setHasnext(false);

        List<Integer> pages = new ArrayList<>();
        pages.add(1);

        setPages(pages);
        List<V> viewList = convertDBItemsToViewItems();
        setList(viewList);

        totalItems = dbItemsOnRequestedPage.size();
        return this;
    }


    private List getQuestionsBySubject(String lang, String subject){
        Subject searchSubject = subjectRepo.findTop1SubjectBySubjectOrRuSubject(subject,subject);

        if(lang.equals(RUS_STRING)){
            return questionService.getQuestionsByTopicsOfSubject(searchSubject.getSubjectId(),RUS);
        }else{
            return questionService.getQuestionsByTopicsOfSubject(searchSubject.getSubjectId(),UZ);
        }

    }

    private List getQuestionsByTopic(String lang, String topic){
        Topic searchTopic = topicRepo.findTop1ByTopicOrRuTopic(topic,topic);

        if(lang.equals(RUS_STRING)){
            return questionService.getQuestionsByTopic(searchTopic.getTopicId(),RUS);
        }else{
            return questionService.getQuestionsByTopic(searchTopic.getTopicId(),UZ);
        }
    }

    private int getIdOfFirstItemOnRequestedPage(){

        int id = 0;

        for(int i = 1; i < requestedPage; i++){
            List<E> entities = repository.findTop10ById(id);
            int size = entities.size();
            E lastEntity = entities.get(size - 1);

            if(lastEntity instanceof Question){
                Question question = (Question) lastEntity;
                id = question.getQuestionId();
            }else if(lastEntity instanceof Course){
                Course course = (Course)lastEntity;
                id = course.getCourseId();
            }else if(lastEntity instanceof Faculty){
                Faculty faculty = (Faculty)lastEntity;
                id = faculty.getFacultyId();
            }else if(lastEntity instanceof  QuestionQA){
                QuestionQA questionQA = (QuestionQA)lastEntity;
                id = questionQA.getQuestionId();
            }else if(lastEntity instanceof Topic){
                Topic topic = (Topic)lastEntity;
                id = topic.getTopicId();
            }else if(lastEntity instanceof University){
                University university = (University)lastEntity;
                id = university.getUniversityId();
            }else if(lastEntity instanceof User){
                User user = (User)lastEntity;
                id = requestedPage;
            }
        }

        return id;
    }

    private List<Integer> getNextAndPreviousPageNumbers(){
        List<Integer> pageNumbers = new ArrayList<>();

        int lastPage = totalItems / PAGE_COUNT;

        if(totalItems % PAGE_COUNT > 0){
            lastPage++;
        }

        if(lastPage < 6 ){
            for(int i = 1; i <= lastPage; i++) {
                pageNumbers.add(i);
            }
        } else if(requestedPage - 2 <= 0){
            for(int i = 1; i < 6; i++) {
                pageNumbers.add(i);
            }
        }else if(requestedPage + 2 <= lastPage){
            pageNumbers.add(requestedPage - 2);
            pageNumbers.add(requestedPage - 1);
            pageNumbers.add(requestedPage);
            pageNumbers.add(requestedPage + 1);
            pageNumbers.add(requestedPage + 2);
        }else{
            pageNumbers.add(lastPage - 4);
            pageNumbers.add(lastPage - 3);
            pageNumbers.add(lastPage - 2);
            pageNumbers.add(lastPage - 1);
            pageNumbers.add(lastPage);
        }

        return pageNumbers;
    }

    private void setPageItems(){
        list = convertDBItemsToViewItems();
    }

    private List<V> convertDBItemsToViewItems(){

        List list = new ArrayList<>();
        E currentClass = null;

        for(E e: dbItemsOnRequestedPage){
            currentClass = e;
            break;
        }

        if(currentClass == null){
            return new ArrayList<V>();
        }

        if(currentClass instanceof Question){
            for(E e: dbItemsOnRequestedPage){
//                todo; handle exception here
                try{
                    Question question = (Question)e;
                    Topic topic = question.getTopic();
                    Subject subject = subjectRepo.findOne(topic.getSubject().getSubjectId());
                    list.add(new QuestionView((Question) e, subject, topic));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }else if(currentClass instanceof Course){
            for(E e: dbItemsOnRequestedPage){
                list.add(new CourseView((Course)e));
            }
        }else if(currentClass instanceof Faculty){
            for(E e: dbItemsOnRequestedPage){
                list.add(new FacultyView((Faculty)e));
            }
        }else if(currentClass instanceof QuestionQA){
            for(E e: dbItemsOnRequestedPage){
                list.add(new QAView((QuestionQA) e));
            }
        }else if(currentClass instanceof Topic){
            for(E e: dbItemsOnRequestedPage){
                Topic topic = (Topic) e;
                int numberOfUzbekQuestions = questionRepo.countQuestionsByTopic_TopicIdAndLang(topic.getTopicId(),UZ);
                int numberOfRusQuestions = questionRepo.countQuestionsByTopic_TopicIdAndLang(topic.getTopicId(),RUS);
                list.add(new TopicView(topic,numberOfUzbekQuestions,numberOfRusQuestions));
            }
        }else if(currentClass instanceof University){
            for(E e: dbItemsOnRequestedPage){
                list.add(new UniversityView((University) e));
            }
        }else if(currentClass instanceof User){
            for(E e: dbItemsOnRequestedPage){
                list.add(new UserView((User) e));
            }
        }

        return list;
    }

    private boolean isPageExist(){
        return (requestedPage - 1) * PAGE_COUNT <= totalItems;
    }

    private boolean isRequestedPageIsFirstPage(){
        return requestedPage == 1;
    }

    private boolean isPreviousPageExist(){
        return requestedPage > 1;
    }

    private boolean isNextPageExist(){
        return totalItems > requestedPage * PAGE_COUNT;
    }

    private List getItemsOnRequestedPageWith(int idOfFirstItemOnRequestedPage){
        return repository.findTop10ById(idOfFirstItemOnRequestedPage);
    }

    public int next(){
        return currentpage + 1;
    }

    public int previous(){
        return currentpage - 1;
    }

    public boolean currentpage(int i){
        return i == currentpage;
    }

    public void setPagination(List<Integer> pages) {
        this.pages = pages;
    }

    public boolean isHasnext() {
        return hasnext;
    }

    public boolean isHasprevious() {
        return hasprevious;
    }
}
