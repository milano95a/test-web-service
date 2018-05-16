package home.model;

import home.model.view.QuestionView;
import java.util.List;

/**
 * Created by AB on 16-Aug-17.
 */

public class PageI {
    List<QuestionView> questions;
    int currentpage;
    List<Integer> pages;
    boolean hasnext;
    boolean hasprevious;

    public int next(){
        return currentpage + 1;
    }

    public int previous(){
        return currentpage - 1;
    }

    public List<QuestionView> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionView> questions) {
        this.questions = questions;
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

    public boolean currentpage(int i){
        return i == currentpage;
    }
}
