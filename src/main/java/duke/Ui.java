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

    private static final String END_MESSAGE = "kthxbye\nRemember to click on the `X` at the top right before leaving.";

    private static final String BAD_DATE_FORMAT_ERROR = "Enter a valid date format! (yyyy-mm-dd)\n";

    private static final String ADD_TASK_COMPLETION_MESSAGE = "Ok can, sure. I have added this task as you wanted.\n";

    private static final String FILE_ERROR = "An error occurred while processing your data file.\n";

    private static final String READ_FILE_ERROR = "There was an error while loading your save file.\n";

    private static final String UNKNOWN_ERROR = "An unknown error has occurred. Try restarting me!\n";

    private static final String NO_MATCHES = "Looks like none of your tasks match with what you are searching for.\n";

    private static final String YES_MATCHES = "Lucky for you, the following Tasks seem to match your search term.\n";

    /**
     * Returns a message about an error with a number.
     *
     * @return The specified string.
     */
    public static String getNumberError() {
        return NUMBER_ERROR;
    }

    /**
     * Returns a welcome message to the user.
     *
     * @return The specified string.
     */
    public static String getWelcomeMessage() {
        return "Hello from\n" + LOGO + "\nWhat can I do for you today?";
    }

    /**
     * Returns a message about empty input.
     *
     * @return The specified string.
     */
    public static String getEmptyInputError() {
        return EMPTY_INPUT_ERROR;
    }

    /**
     * Returns a message about an error due to no Tasks.
     *
     * @return The specified string.
     */
    public static String getNoTaskError() {
        return NO_TASK_ERROR;
    }

    /**
     * Returns a message about an error with a Task.
     *
     * @return The specified string.
     */
    public static String getTaskError() {
        return TASK_ERROR;
    }

    /**
     * Returns a message confirming that Task is completed.
     *
     * @return The specified string.
     */
    public static String getTaskCompleted(Task task) {
        return TASK_COMPLETED + task.toString();
    }

    /**
     * Returns a message reminding the user about the number of Tasks registered.
     *
     * @return The specified string.
     */
    public static String getTaskNumberReminder(int number) {
        return "Now you have " + number + " tasks remaining. Get to work!";
    }

    /**
     * Returns a message about an error due to bad input.
     *
     * @return The specified string.
     */
    public static String getBadInputError() {
        return BAD_INPUT_ERROR;
    }

    /**
     * Returns a message when the program ends.
     *
     * @return The specified string.
     */
    public static String getEndMessage() {
        return END_MESSAGE;
    }

    /**
     * Returns a message about an error due to a bad date format.
     *
     * @return The specified string.
     */
    public static String getBadDateFormatError() {
        return BAD_DATE_FORMAT_ERROR;
    }

    /**
     * Returns a message informing the user that a Task was successfully added.
     *
     * @return The specified string.
     */
    public static String getAddTaskCompletionMessage() {
        return ADD_TASK_COMPLETION_MESSAGE;
    }

    /**
     * Returns a message about an error with a file.
     *
     * @return The specified string.
     */
    public static String getFileError() {
        return FILE_ERROR;
    }

    public static String getReadFileError() {
        return READ_FILE_ERROR;
    }

    /**
     * Returns a message about an unknown error.
     *
     * @return The specified string.
     */
    public static String getUnknownError() {
        return UNKNOWN_ERROR;
    }

    /**
     * Returns a message about no matches found.
     *
     * @return The specified string.
     */
    public static String getNoMatch() {
        return NO_MATCHES;
    }

    /**
     * Returns a message that a match has been found.
     *
     * @return The specified string.
     */
    public static String getMatch() {
        return YES_MATCHES;
    }
}
