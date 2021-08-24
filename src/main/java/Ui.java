import java.util.Scanner;

/**
 * Represents the UI of the Duke program.
 */
public class Ui {
    /** Greeting message to be printed when the program starts */
    private static final String GREETING_MESSAGE =
            "____________________________________________________________\n" +
                    "Hello! I'm Duke\n" +
                    "What can I do for you?\n" +
                    "____________________________________________________________\n";
    /** Scanner used to read commands */
    private Scanner input;
    /** Parser used to interpret commands */
    private Parser parser;
    private Duke duke;

    /**
     * Constructor for the class `Ui`.
     */
    public Ui(Duke duke) {
        this.duke = duke;
        System.out.println(Ui.GREETING_MESSAGE);
        this.input = new Scanner(System.in);
        this.parser = new Parser(duke);
    }

    /**
     * Reads and returns command from the keyboard.
     *
     * @return The command received.
     */
    private String getCommand() {
        return this.input.nextLine().trim();
    }

    /**
     * Prints out output of a processor.
     *
     * @return Whether the program is still running.
     */
    public boolean getCommandOutput() {
        String command = this.getCommand();
        return this.parser.parse(command, this);
    }

    /**
     * Returns the format of a task.
     *
     * @param taskType Type of the task.
     * @param timeFormat Time format of the task.
     * @return Command format of the task.
     */
    public String getCommandFormat(String taskType, String timeFormat) {
        return String.format("Please follow the format:\n%s <task description> %s", taskType, timeFormat);
    }

    /**
     * Prints out error message indicating that file containing data is not found.
     */
    public static void showFileNotFoundError() {
        DukeException dukeException = new DukeException(
                "☹ OOPS!!! The file cannot be found. A new file has been created, please try again!");
        System.out.println(dukeException);
    }

    /**
     * Prints out error message indicating that task number is invalid.
     */
    public void showInvalidTaskNoError() {
        DukeException dukeException = new DukeException("☹ OOPS!!! The task number is invalid.");
        System.out.println(dukeException);
    }

    /**
     * Prints out error message indicating that time is invalid.
     *
     * @param timeFormat A string representation of time format.
     */
    public void showInvalidTimeError(String timeFormat) {
        DukeException dukeException = new DukeException(
                "☹ OOPS!!! The time is invalid.\nPlease input time in this form:\n" + timeFormat);
        System.out.println(dukeException);
    }

    public void showInvalidCommandError() {
        DukeException dukeException = new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(dukeException);
    }

    /**
     * Prints out error message indicating that details are missing in the command.
     *
     * @param missingDetails Missing details as a string.
     * @param taskType Type of the task.
     * @param timeFormat Time format of the task.
     */
    public void showMissingDetailsError(String missingDetails, String taskType, String timeFormat) {
        String errorMessage = String.format("☹ OOPS!!! The %s of %s cannot be empty.\n", missingDetails, taskType) +
                this.getCommandFormat(taskType, timeFormat);
        DukeException dukeException = new DukeException(errorMessage);
        System.out.println(dukeException);
    }

    /**
     * Prints out error message indicating that a task cannot occupy multiple time slots.
     *
     * @param taskType Type of the task.
     */
    public void showMultipleTimeSlotsError(String taskType) {
        String errorMessage = String.format("☹ OOPS!!! %s cannot occupy multiple time slots.", taskType);
        DukeException dukeException = new DukeException(errorMessage);
        System.out.println(dukeException);
    }
}
