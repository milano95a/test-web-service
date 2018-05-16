package home.utils;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

/**
 * Created by AB on 11-Sep-17.
 */

@Component
public class JsonUtil {
    public String getStringFromJsonObject(JsonObject jsonObject, String memberName, String defaultString){
        try {
            String jsonString = jsonObject.get(memberName).getAsString();
            return jsonString != null ? jsonString : defaultString;
        }catch (Exception e){
            return defaultString;
        }
    }
}
