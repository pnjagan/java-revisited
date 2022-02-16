package javabook;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {
    private static Logger logger = Logger.getLogger("Default") ;

    static {
        logger.setLevel(Level.ALL);
        String prop = "handlers= java.util.logging.ConsoleHandler";
    }

    public static void log (String s) {
        Logger.getLogger("wait-notify-coding").info(s);
    }

}
