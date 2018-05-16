package home.utils;

import home.entity.*;
import home.model.view.*;
import home.repo.ISubjectRepo;
import home.repo.ITopicRepo;
import home.service.BaseService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static home.constant.Constants.PAGE_COUNT;

public class PageObject<V, E> {

    ISubjectRepo subjectRepo;
    ITopicRepo topicRepo;

    List<V> list;
    int currentpage;
    List<Integer> pages;
    boolean hasnext;
    boolean hasprevious;

    boolean haslast = true;
    public String first(){
        return "/first";
    }

    public String last(){
        return "/last";
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
    public List<V> getList() {
        return list;
    }

    public void setList(List<V> list) {
        this.list = list;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public boolean isHasnext() {
        return hasnext;
    }

    public void setHasnext(boolean hasnext) {
        this.hasnext = hasnext;
    }

    public boolean isHasprevious() {
        return hasprevious;
    }

    public void setHasprevious(boolean hasprevious) {
        this.hasprevious = hasprevious;
    }

    public PageObject getPageObject(int pageNumber, BaseService service){

        List<E> dbList = new ArrayList<>();
        int count = service.count();

        if(pageNumber == 1){
            dbList = service.findTop10ById(0);
        }else{
            if((pageNumber - 1) * PAGE_COUNT <= count){
                int id = getIdOfStartPage(pageNumber, service);
                dbList = service.findTop10ById(id);
            }
        }

        PageObject<V,E> page = new PageObject<>();
        page.setCurrentpage(pageNumber);

        if(pageNumber > 1){
            page.setHasprevious(true);
        }else{
            page.setHasprevious(false);
        }

        if(count > pageNumber * PAGE_COUNT){
            page.setHasnext(true);
        }else{
            page.setHasnext(false);
        }

        List<Integer> pages = getPageNumbers(pageNumber,count);

        page.setPages(pages);
        List<V> viewList = convert(dbList);
        page.setList(viewList);

        return page;
    }

    public PageObject getPageObject(BaseService service, ITopicRepo topicRepo, ISubjectRepo subjectRepo, String questionSearchText){

        this.topicRepo = topicRepo;
        this.subjectRepo = subjectRepo;

        List<E> dbList = service.findBySearchText(questionSearchText);
        int count = service.count();

//        if(pageNumber == 1){
//            dbList = service.findTop10ById(0);
//        }else{
//            if((pageNumber - 1) * PAGE_COUNT <= count){
//                int id = getIdOfStartPage(pageNumber, service);
//                dbList = service.findTop10ById(id);
//            }
//        }



        PageObject<V,E> page = new PageObject<>();
        page.setCurrentpage(1);

//        if(pageNumber > 1){
//            page.setHasprevious(true);
//        }else{
//        }
        page.setHasprevious(false);

//        if(count > pageNumber * PAGE_COUNT){
//            page.setHasnext(true);
//        }else{
//        }

        page.setHasnext(false);

        List<Integer> pages = new ArrayList<>();
        pages.add(1);

        page.setPages(pages);
        List<V> viewList = convert(dbList);
        page.setList(viewList);

        return page;
    }

    public PageObject getPageObject(int pageNumber, BaseService service, ITopicRepo topicRepo, ISubjectRepo subjectRepo){

        this.topicRepo = topicRepo;
        this.subjectRepo = subjectRepo;

        List<E> dbList = new ArrayList<>();
        int count = service.count();

        if(pageNumber == 1){
            dbList = service.findTop10ById(0);
        }else{
//            if((pageNumber - 1) * SEARCH_PAGE_COUNT <= count){
                int id = getIdOfStartPage(pageNumber, service);
                dbList = service.findTop10ById(id);
//            }
        }

        PageObject<V,E> page = new PageObject<>();
        page.setCurrentpage(pageNumber);

        if(pageNumber > 1){
            page.setHasprevious(true);
        }else{
            page.setHasprevious(false);
        }

        if(count > pageNumber * PAGE_COUNT){
            page.setHasnext(true);
        }else{
            page.setHasnext(false);
        }

        List<Integer> pages = getPageNumbers(pageNumber,count);

        page.setPages(pages);
        List<V> viewList = convert(dbList);
        page.setList(viewList);

        return page;
    }

    private int getIdOfStartPage(int pageNumber, BaseService service){

        int id = 0;

        for(int i = 1; i < pageNumber; i++){
            List<E> entities = service.findTop10ById(id);
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
                id = pageNumber;
            }
        }

        return id;
    }

    private List<Integer> getPageNumbers(int currentPageNumber, int numberOfQuestions){
        List<Integer> pageNumbers = new ArrayList<>();

        int lastPage = numberOfQuestions / PAGE_COUNT;

        if(numberOfQuestions % PAGE_COUNT > 0){
            lastPage++;
        }

        if(lastPage < 6 ){
            for(int i = 1; i <= lastPage; i++) {
                pageNumbers.add(i);
            }
        } else if(currentPageNumber - 2 <= 0){
            for(int i = 1; i < 6; i++) {
                pageNumbers.add(i);
            }
        }else if(currentPageNumber + 2 <= lastPage){
            pageNumbers.add(currentPageNumber - 2);
            pageNumbers.add(currentPageNumber - 1);
            pageNumbers.add(currentPageNumber);
            pageNumbers.add(currentPageNumber + 1);
            pageNumbers.add(currentPageNumber + 2);
        }else{
            pageNumbers.add(lastPage - 4);
            pageNumbers.add(lastPage - 3);
            pageNumbers.add(lastPage - 2);
            pageNumbers.add(lastPage - 1);
            pageNumbers.add(lastPage);
        }

        return pageNumbers;
    }

    private List<V> convert(List<E> entityList){

        List viewList = new ArrayList<>();
        E currentClass = null;

        for(E e: entityList){
            currentClass = e;
            break;
        }

        if(currentClass == null){
            return new ArrayList<V>();
        }

        if(currentClass instanceof Question){
            for(E e: entityList){
                Question question = (Question)e;
                Topic topic = question.getTopic();
                Subject subject = subjectRepo.findOne(topic.getSubject().getSubjectId());
                viewList.add(new QuestionView((Question) e, subject, topic));
            }
        }else if(currentClass instanceof Course){
            for(E e: entityList){
                viewList.add(new CourseView((Course)e));
            }
        }else if(currentClass instanceof Faculty){
            for(E e: entityList){
                viewList.add(new FacultyView((Faculty)e));
            }
        }else if(currentClass instanceof QuestionQA){
            for(E e: entityList){
                viewList.add(new QAView((QuestionQA) e));
            }
        }else if(currentClass instanceof Topic){
            for(E e: entityList){
                viewList.add(new TopicView((Topic) e));
            }
        }else if(currentClass instanceof University){
            for(E e: entityList){
                viewList.add(new UniversityView((University) e));
            }
        }else if(currentClass instanceof User){
            for(E e: entityList){
                viewList.add(new UserView((User) e));
            }
        }

        return viewList;
    }
}
