package home.config;

import com.google.gson.Gson;
import home.entity.Ex;
import home.repo.IExRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExHandler {

    @Autowired
    IExRepo exRepo;

    @ExceptionHandler
    protected void handleException(Exception ex, WebRequest request){
        ex.printStackTrace();
        String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();

        Object paramsObj = request.getParameterMap();
        Gson gson = new Gson();
        String params = gson.toJson(paramsObj);

        Object firstStackTraceObj = ex.getStackTrace()[0];
        String firstStackTrace = gson.toJson(firstStackTraceObj);

        Object stackTraceObj = ex.getStackTrace();
        String stackTrace = gson.toJson(stackTraceObj);

        String message = ex.getMessage();

        Object causeObj = ex.getCause();
        String cause = gson.toJson(causeObj);

        Ex exception = new Ex();
        exception.setCause(cause);
        exception.setHeadStackTrace(firstStackTrace);
//        exception.setStackTrace(stackTrace);
        exception.setMessage(message);
        exception.setParams(params);
        exRepo.save(exception);
    }
}
