package home.service;

import java.util.List;

public interface BaseService {
    int count();
    List findTop10ById(int id);
    List findBySearchText(String searchText);
    List findFirst();
    List findLast();
}
