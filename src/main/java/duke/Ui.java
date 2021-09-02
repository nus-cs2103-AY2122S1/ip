package duke;

/**
 * A class containing methods that return messages in response
 * to user inputs and errors.
 *
 * @author Toh Wang Bin
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String NUMBER_ERROR = "Enter a valid number! Or do you not know basic math?\n";

    private static final String EMPTY_INPUT_ERROR = "...why so quiet? Come on, say something to me!\n";

    private static final String NO_TASK_ERROR = "There are currently no tasks! Stop being so lazy "
            + "and start adding your tasks!\n";

    private static final String TASK_ERROR = "That task does not exist! Don't try to trick me!\n";

    private static final String TASK_COMPLETED = "Ok, very nice. I have set the following task as completed.\n";

    private static final String BAD_INPUT_ERROR = "I could not understand your input. Please stop speaking gibberish"
            + " to me!\n";

    private static final String END_MESSAGE = "kthxbye\n";

    private static final String BAD_DATE_FORMAT_ERROR = "Enter a valid date format! (yyyy-mm-dd)\n";

    private static final String ADD_TASK_COMPLETION_MESSAGE = "Ok can, sure. I have added this task as you wanted.\n";

    private static final String FILE_ERROR = "An error occurred while processing your data file.\n";

    private static final String READ_FILE_ERROR = "There was an error while loading your save file.\n";

    private static final String UNKNOWN_ERROR = "An unknown error has occurred. Try restarting me!\n";

    private static final String NO_MATCHES = "Looks like none of your tasks match with what you are searching for.\n";

    private static final String YES_MATCHES = "Lucky for you, the following Tasks seem to match your search term.\n";

    /**
     * returns a message about an error with a number.
     */
    public static String getNumberError() {
        return NUMBER_ERROR;
    }

    /**
     * returns a welcome message to the user.
     */
    public static String getWelcomeMessage() {
        return "Hello from\n" + LOGO + "\nWhat can I do for you today?";
    }

    /**
     * returns a message about empty input.
     */
    public static String getEmptyInputError() {
        return EMPTY_INPUT_ERROR;
    }

    /**
     * returns a message about an error due to no Tasks.
     */
    public static String getNoTaskError() {
        return NO_TASK_ERROR;
    }

    /**
     * returns a message about an error with a Task.
     */
    public static String getTaskError() {
        return TASK_ERROR;
    }

    /**
     * returns a message confirming that Task is completed.
     */
    public static String getTaskCompleted(Task task) {
        return TASK_COMPLETED + task.toString();
    }

    /**
     * returns a message reminding the user about the number of Tasks registered.
     */
    public static String getTaskNumberReminder(int number) {
        return "Now you have " + number + " tasks remaining. Get to work!";
    }

    /**
     * returns a message about an error due to bad input.
     */
    public static String getBadInputError() {
        return BAD_INPUT_ERROR;
    }

    /**
     * returns a message when the program ends.
     */
    public static String getEndMessage() {
        return END_MESSAGE;
    }

    /**
     * returns a message about an error due to a bad date format.
     */
    public static String getBadDateFormatError() {
        return BAD_DATE_FORMAT_ERROR;
    }

    /**
     * returns a message informing the user that a Task was successfully added.
     */
    public static String getAddTaskCompletionMessage() {
        return ADD_TASK_COMPLETION_MESSAGE;
    }

    /**
     * returns a message about an error with a file.
     */
    public static String getFileError() {
        return FILE_ERROR;
    }

    public static String getReadFileError() {
        return READ_FILE_ERROR;
    }

    /**
     * returns a message about an unknown error.
     */
    public static String getUnknownError() {
        return UNKNOWN_ERROR;
    }

    /**
     * returns a message about no matches found.
     */
    public static String getNoMatch() {
        return NO_MATCHES;
    }

    /**
     * returns a message that a match has been found.
     */
    public static String getMatch() {
        return YES_MATCHES;
    }
}
