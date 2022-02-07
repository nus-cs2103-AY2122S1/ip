package duke;


/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String LOGO =
            "                    _              _        \n" +
                    "                   |  |             |  |       \n" +
                    " _ __ _     __  |  |  __      _|  | _   _ \n" +
                    "| '_ ` _  \\ / _ \\|  | / _ \\  / _`  ||  |  |  |\n" +
                    "|  | | | |  ||  __/|  || (_) | | (_|  | |  |_|  |\n" +
                    "|_| |_| |_| \\___| |_| \\__/  \\__,_| \\___, |\n" +
                    "                                          __/   |\n" +
                    "                                          |___/ \n";

    public static String HELP_TEXT =
            "- to enter a task:\n" +
                    " 'todo' + description\n" +
                    " 'event' + description +'/' + date(yyyy-MM-dd)\n" +
                    " 'deadline' + description + '/' + date(yyyy-MM-dd)\n" +
                    "- to mark a task as done:\n" +
                    " 'done' + taskNumber\n" +
                    "- to delete a task:\n" +
                    " 'delete' + taskNumber\n" +
                    "- to display all tasks:\n" +
                    " 'list'\n" +
                    "- to search for a task:\n" +
                    " 'find' + searchWord\n" +
                    "- to save all tasks and exit:\n" +
                    " 'bye' \n" +
                    "- to snooze a task/deadline: \n" +
                    " 'snooze' + taskNumber + date(yyyy-MM-dd)";

    public static String WELCOME = "Hello from\n" + LOGO + "Type 'help' to see how to talk to me!";

    /**
     * Constructor of Ui.
     */
    public Ui() {
    }


    /**
     * Output exit message.
     */
    public String exit() {
        return ("Byebye ~ nya");
    }


    /**
     * Output the error message.
     *
     * @param error Error name.
     */
    public static String showError(String error) {
        return ("OvO I found the following errors! " + error);
    }

}
