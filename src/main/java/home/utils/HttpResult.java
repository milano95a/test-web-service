package home.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class HttpResult {

    public Object unauthorized(String message){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(makeMessageWith("unauthorized",message));
    }

    public Object badRequest(String message){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(makeMessageWith("bad request",message));
    }

    public Object ok(Object body){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }

    public Object ok(String title, String body){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(makeMessageWith(title,body));
    }

    public Object serverError(String message){
        HashMap<String,String> m = new HashMap<>();
        m.put("server error",message);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(m);
    }

    private HashMap<String,String> makeMessageWith(String title, String message){
        HashMap<String,String> m = new HashMap<>();
        m.put(title,message);
        return m;
    }
}
