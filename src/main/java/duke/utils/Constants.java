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
    public static final String EDIT_FORMAT = "\nedit [task_no] [field] [revised_contents]\n\n" +
            "* [task_no] refers to the index of the element which you wish to edit in the task list\n" +
            "* [field] refers to three attributes of the specified task types - description, date and dateTime\n\n" +
            "Please use the keywords for the fields:\n" +
            "* desc - to change the description of a task\n" +
            "* date - to change the date of a deadline\n" +
            "* datetime - to change the date and time of an event";

    private Constants() {
    }

}

