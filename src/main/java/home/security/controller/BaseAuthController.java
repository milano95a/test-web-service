package home.security.controller;

import home.entity.User;
import home.utils.Repos;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseAuthController extends Repos {

    public User user(){
        if(SecurityContextHolder.getContext().getAuthentication()!= null) {
            return (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        }
        return null;
    }

//    public Object unauthorized(String message){
//        HashMap<String,String> m = new HashMap<>();
//        m.put("unauthorized",message);
//
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(m);
//    }
//
//    public Object ok(String title, String message){
//        HashMap<String,String> m = new HashMap<>();
//        m.put(title,message);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(m);
//    }
//
//    public Object serverError(String message){
//        HashMap<String,String> m = new HashMap<>();
//        m.put("server error",message);
//
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(m);
//    }
//
//    public Object badRequest(String message){
//        HashMap<String,String> m = new HashMap<>();
//        m.put("bad request",message);
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(m);
//    }

}
