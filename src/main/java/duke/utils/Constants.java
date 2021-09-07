package duke.utils;

/**
 * The Constants class contains all the constants which are used in the UI and Parser classes.
 */
public class Constants {

    public static final String HELLO = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String TODO_FORMAT = "\nPlease use the following format:\ntodo [todo_description]";
    public static final String DEADLINE_FORMAT = "\nPlease use the following format:\n"
            + "deadline [deadline_description] /by [deadline_date]";
    public static final String EVENT_FORMAT = "\nPlease use the following format:\n"
            + "event [event_description] /at [event_date_and_time]";

    private Constants() {
    }

}
