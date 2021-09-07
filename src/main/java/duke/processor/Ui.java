package duke.processor;

import java.time.format.DateTimeFormatter;

/**
 * Methods for printing. Defines format, indentation etc.
 * 
 * @author Tianqi-Zhu
 */
public class Ui {
    public static final String LINE_STRING = "----------------------------------------";
    public static final String SPACE_STRING = "    ";
    public static final String DECORATOR_STRING = SPACE_STRING + LINE_STRING; 
    public static final DateTimeFormatter OUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
    public static final DateTimeFormatter OUT_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    /**
     * return exit message.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }
    
    /**
     * Print greeting messgae.
     */
    public static String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello, I am your personal asistant\n" + logo + SPACE_STRING + "What can I do for you?";
    }
}