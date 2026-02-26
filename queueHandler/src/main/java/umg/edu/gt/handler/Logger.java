package umg.edu.gt.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger propio (sin librerías externas).
 * Formato: [LOG] yyyy-MM-dd HH:mm:ss - mensaje
 */
public final class Logger {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Logger() {}

    public static void log(String message) {
        System.out.println("[LOG] " + SDF.format(new Date()) + " - " + message);
    }

    public static void warn(String message) {
        System.out.println("[WARN] " + SDF.format(new Date()) + " - " + message);
    }

    public static void error(String message) {
        System.err.println("[ERROR] " + SDF.format(new Date()) + " - " + message);
    }
}
