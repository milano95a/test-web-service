package home.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Created by AB on 06-Sep-17.
 */
public class SHA {

    public static String hash(String raw){
        return Hashing.sha256().hashString(raw, StandardCharsets.UTF_8).toString();
    }

}
