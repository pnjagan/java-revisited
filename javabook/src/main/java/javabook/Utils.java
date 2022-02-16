package javabook;

import java.util.Date;

public class Utils {
    public static void log(String s) {
        System.out.println(s);
    }

    public static String getTime() {
        return new Date().toString();
    }
}
