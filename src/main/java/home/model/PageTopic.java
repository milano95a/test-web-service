package home.model;

import home.entity.Topic;
import home.model.view.TopicView;

import java.util.List;

/**
 * Created by AB on 22-Aug-17.
 */
public class PageTopic {

    List<TopicView> list;
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

    public boolean currentpage(int i){
        return i == currentpage;
    }

    public List<TopicView> getList() {
        return list;
    }

    public void setList(List<TopicView> list) {
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
}
