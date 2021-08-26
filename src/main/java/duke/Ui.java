package duke;

public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String NUMBER_ERROR = "Enter a valid number! Or do you not know basic math?";

    private static final String EMPTY_INPUT_ERROR = "...why so quiet? Come on, say something to me!";

    private static final String NO_TASK_ERROR = "There are currently no tasks! Stop being so lazy " +
            "and start adding your tasks!";

    private static final String TASK_ERROR = "That task does not exist! Don't try to trick me!";

    private static final String TASK_COMPLETED = "Ok, very nice. I have set the following task as completed.";

    private static final String BAD_INPUT_ERROR = "I could not understand your input. Please stop speaking gibberish to me!";

    private static final String END_MESSAGE = "kthxbye";

    private static final String BAD_DATE_FORMAT_ERROR ="Enter a valid date format! (yyyy-mm-dd)";

    private static final String ADD_TASK_COMPLETION_MESSAGE = "Ok can, sure. I have added this task as you wanted.";

    private static final String FILE_ERROR = "An error occurred while processing your data file.";

    private static final String UNKNOWN_ERROR = "An unknown error has occurred. Try restarting me!";

    public static void printNumberError() {
        System.out.println(NUMBER_ERROR);
    }

    public static void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO + "\nWhat can I do for you today?");
    }

    public static void printEmptyInputError() {
        System.out.println(EMPTY_INPUT_ERROR);
    }

    public static void printNoTaskError() {
        System.out.println(NO_TASK_ERROR);
    }

    public static void printTaskError() {
        System.out.println(TASK_ERROR);
    }

    public static void printTaskCompleted() {
        System.out.println(TASK_COMPLETED);
    }

    public static void printTaskNumberReminder(int number) {
        System.out.println("Now you have " + number + " tasks remaining. Get to work!");
    }

    public static void printBadInputError() {
        System.out.println(BAD_INPUT_ERROR);
    }

    public static void printEndMessage() {
        System.out.println(END_MESSAGE);
    }

    public static void printBadDateFormatError() {
        System.out.println(BAD_DATE_FORMAT_ERROR);
    }

    public static void printAddTaskCompletionMessage() {
        System.out.println(ADD_TASK_COMPLETION_MESSAGE);
    }

    public static void printFileError() {
        System.out.println(FILE_ERROR);
    }

    public static void printUnknownError() {
        System.out.println(UNKNOWN_ERROR);
    }
}
