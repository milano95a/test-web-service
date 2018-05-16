package home.service;

import home.entity.User;
import home.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static home.constant.Constants.PAGE_COUNT;

@Service
public class UserService implements BaseService{

    @Autowired
    IUserRepo userRepo;

    @Override
    public int count() {
        List<User> users = userRepo.findAll();
        return users.size();
    }

    @Override
    public List findTop10ById(int id) {
        List<User> userList = userRepo.findAll();
        Integer count = userList.size();
        List<User> users = new ArrayList<>();

        if(userList.size() < 1){
            return users;
        }

        if(id == 1 || id == 0){
            for(int starting = 0; starting < 10; starting++){

//                if(userList.size() > starting){
                    if(starting < count){
                        users.add(userList.get(starting));
                    }
//                }
            }
        }else {
            id -= 1;
            id *= PAGE_COUNT;

            int i = id % 10;
            for (int starting = id - 1; starting < (id + 10); starting++) {
                if (starting < count) {
                    users.add(userList.get(starting));
                }
            }
        }
        return users;
    }

    @Override
    public List findBySearchText(String searchText) {
        return null;
    }

    @Override
    public List findFirst() {
        return null;
    }

    @Override
    public List findLast() {
        return null;
    }
}
