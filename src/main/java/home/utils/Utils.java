package home.utils;

import java.awt.*;
import java.util.Random;

/**
 * Created by AB on 29-Jul-17.
 */
public class Utils {

//    generate random number
    public static int r(int upperBound){
        return new Random().nextInt(upperBound);
    }
}
