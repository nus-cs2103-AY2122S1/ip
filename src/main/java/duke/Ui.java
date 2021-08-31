package duke;

public class Ui {
    private static final String startMessage = "____________________________________________________________\n"
            + "Hello! I'm Duke. \nFor "
            + "events and deadlines, be sure to state the due date or the event timing as such: "
            + "'XXX /by dd-MM-yyyy HH:mm'\n"
            + "____________________________________________________________";
    private static final String endMessage = "____________________________________________________________\n"
            + "Bye! Hope to see you again soon!\n"
            + "____________________________________________________________";

    private static final String taskDoesNotExistError = "____________________________________________________________\n"
            + "☹ OOPS!!! The task you chose does not exist. Use the 'list' command to check the items in your list.\n"
            + "____________________________________________________________";

    /**
     * Sends a customised error message, usually from DukeExceptions.
     * @param message the error message that is printed out to the user.
     */
    public static void customErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Sends Duke's start message when Duke awakens.
     */
    public static void sendStartMessage() {
        System.out.println(Ui.startMessage);
    }

    /**
     * Sends Duke's end message when Duke goes to sleep.
     */
    public static String sendEndMessage() {
        return Ui.endMessage;
    }

    /**
     * Prints a message to the user about the task that the user just deleted.
     * @param task The task that got deleted.
     * @param taskListSize The updated TaskList size.
     */
    public static String deletedTaskMessage(TaskItem task, int taskListSize) {
        String body = "";
        if (taskListSize == 1) {
            /* System.out.println("Now you have " + 1 + " task in the list."); */
            body = "Now you have " + 1 + " task in the list.\n";
        }
        if (taskListSize > 1) {
            /* System.out.println("Now you have " + (Duke.listIndex) + " tasks in your list."); */
            body = "Now you have " + taskListSize + " tasks in your list.\n";
        }
        return "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + body
                + "____________________________________________________________";
    }

    /**
     * Prints a notification to the user about a Task that the user just marked as complete.
     * @param task The Task that was marked as complete.
     */
    public static String completedTaskMessage(TaskItem task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________");
        return "____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + task.toString()
                + "\n____________________________________________________________";
    }

    /**
     * Sends a message to the user when the user's input for the date/time for Events and Deadlines are invalid inputs.
     */
    public static void dateTimeError() {
        System.out.println("Please follow the specified format for entering the date and time of the deadline.");
    }
}
