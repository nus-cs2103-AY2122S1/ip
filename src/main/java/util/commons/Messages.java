package util.commons;

public class Messages {


    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String GREETINGS = "Greetings! I'm Duke\n\tWhat can I do for you?";
    public static final String BYE = "Godspeed young padawan!";
    public static final String HLINE = "\t----------------------------";




    //Messages that have to be formatted to include tasks
    public static final String TASK_ADDED =
            "Roger! I have added this task.\n\t"
                    + "%s\n\t"
                    + "Now you have %d tasks in the list.\n\t";
    public static final String TASK_DELETED =
            "The following task has been removed\n\t"
                    + "%s\n\t"
                    + "Now you have %d tasks remaining";
    public static final String TASK_NO_DESCRIPTOR_ERROR = "☹ OOPS!!! The description of a %s cannot be empty.";

    public static final String TASK_NOT_UNDERSTOOD_ERROR = "☹ OOPS!!! I do no know what to do";

    public static final String TASK_ALREADY_ADDED = "Task already added: %s";
    public static final String TASK_COMPLETE =
            "Nice, I've marked this task as done\n\t"
                    + "%s";


    //Error messages
    public static final String EVENT_NO_INPUT_ERROR_MESSAGE =
            "☹ OOPS!!! The event deadline must be filled in prefixed by /at";
    public static final String INVALID_DONE_INPUT =
            "☹ OOPS!!! The input integer is invalid for the task list.";

    public static final String DEADLINE_NO_INPUT_ERROR_MESSAGE =
            "☹ OOPS!!! The deadline deadline must be filled in prefixed by /by";


}
