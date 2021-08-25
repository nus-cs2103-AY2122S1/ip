package duke;

/**
 * Class that deals with all the interactions with the user.
 */
public class Ui {

    private static final String LINE = "--------------------------------------------------------------------------------------";

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello! I'm Duke, your personal task manager!\n"
            + "What can I do for you?";

    private static final String HELP_MESSAGE = "Add tasks by commands: todo, deadline, event\n"
            + "E.g. todo run, deadline hw /by yyyy-mm-dd {time}, event meeting /at {date and/or time}\n"
            + "To view list, type 'list'\n"
            + "To delete or mark task as done, type 'delete {task number}' or 'done {task number}'\n"
            + "To exit, type 'bye'";


    public Ui() {}

    /**
     * Method to show welcome message to user
     */
    public static void showWelcomeMessage() {
        showToUser(LOGO, WELCOME_MESSAGE, LINE, HELP_MESSAGE, LINE);
    }

    /**
     * Method to show message to warn user that task number is missing
     */
    public static void warningMissingNumber() {
        String message = "OOPS!!! To delete a task, the task number must be stated.";
        showToUser(LINE, message, LINE);
    }

    /**
     * Method to show message to warn user that keyword is missing
     */
    public static void warningMissingKeyword() {
        String message = "OOPS!!! To find a task, a keyword must be stated.";
        showToUser(LINE, message, LINE);
    }

    /**
     * Method that warns user that there is a missing description for the task.
     * @param command
     */
    public static void warningMissingDescription(String command) {
        switch (command) {
        case "todo":
            String todoMessage = "OOPS!!! The description of a todo cannot be empty.";
            showToUser(LINE, todoMessage, LINE);
            break;
        case "deadline":
            String deadlineMessage = "OOPS!!! The description of a deadline cannot be empty.";
            showToUser(LINE, deadlineMessage, LINE);
            break;
        case "event":
            String eventMessage = "OOPS!!! The description of an event cannot be empty.";
            showToUser(LINE, eventMessage, LINE);
            break;
        }
    }

    /**
     * Method to show goodbye message when user exits programme with command bye
     */
    public static void showGoodbyeMessage() {
        String message = "     Bye. Hope to see you again soon!";
        showToUser(LINE, message, LINE);
    }

    /**
     * Method to show message prompting user that command is invalid or wrong
     */
    public static void showInvalidInputMessage() {
        String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        showToUser(LINE, message, LINE);
    }

    /**
     * Method to show message after successfully marking task as complete
     */
    public static void showCompletedMessage() {
        String message = "Nice! I've marked this task as done:";
        showToUser(LINE, message);
    }

    /**
     * Method to show line divider
     */
    public static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Method to show added task, along with uodated number of tasks in list
     * @param taskName
     * @param numTasks
     */
    public static void showAddedTask(String taskName, int numTasks) {
        String successfulMessage = "Got it. I've added this task:";
        String updatedListNumber = "Now you have " + numTasks + " task(s) in the list.";
        showToUser(LINE, successfulMessage, taskName, updatedListNumber, LINE);
    }

    /**
     * Shows list of all the tasks that have not been deleted
     */
    public static void showList() {
        String message = "Here are the tasks in your list:";
        showToUser(LINE, message);
    }

    /**
     * Shows message if number does not correspond to any task
     */
    public static void noTask() {
        String message = "No such task exists.";
        showToUser(LINE, message, LINE);
    }

    /**
     * Show message upon successfully deleting a task
     */
    public static void showSuccessfulDelete() {
        String successfulMessage = "Noted I've removed this task:";
        showToUser(LINE, successfulMessage);
    }

    /**
     * Shows updated number of tasks in the list
     * @param numTasks
     */
    public static void showUpdatedNumber(int numTasks) {
        String updatedListNumber = "Now you have " + numTasks + " task(s) in the list.";
        showToUser(updatedListNumber, LINE);
    }

    /**
     * Shows message that warns user that due date of a deadline cannot be empty
     */
    public static void missingDeadline() {
        System.out.println("---------------------------------------------");
        System.out.println("OOPS!!! The due date of a deadline cannot be empty.");
        System.out.println("Use format \"/by yyyy-mm-dd {time}\" when providing due date.");
        System.out.println("---------------------------------------------");
    }

    /**
     * Shows message that warns user that details of an event cannot be empty
     */
    public static void missingEventDetails() {
        System.out.println("---------------------------------------------");
        System.out.println("OOPS!!! The details of an event cannot be empty.");
        System.out.println("Please input time, day, or - if details unknown.");
        System.out.println("---------------------------------------------");
    }

    /**
     * Shows message upon successful search of keyword
     */
    public static void showSuccessfulFind() {
        String message = "Here are the matching tasks in your list:";
        showToUser(LINE, message);
    }

    /**
     * Shows message upon unsuccessful search of keyword
     */
    public static void showUnsuccessfulFind() {
        showToUser("There are no tasks with this keyword.");
    }

    /**
     * Shows all tasks with keyword
     */
    public static void showResults(int count, String description) {
        System.out.println(count + ". " + description);
    }

    /**
     * method that takes in mutliple strings to form a message shown to the user
     * @param message
     */
    public static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }


}
