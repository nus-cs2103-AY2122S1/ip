package duke.ui;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.Scanner;

/**
 * Represents UI of the Duke program.
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

    /**
     * Constructor for the class `Ui`.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println(Ui.GREETING_MESSAGE);
    }

    /**
     * Returns command received from keyboard.
     *
     * @return Command received.
     */
    public String getCommand() {
        return this.input.nextLine().trim();
    }

    /**
     * Prints the output of a command.
     *
     * @param command Processed command.
     */
    public void showCommandOutput(Command command) {
        System.out.println(command);
    }

    /**
     * Returns the format of a task.
     *
     * @param taskType Type of the task.
     * @param timeFormat Time format of the task.
     * @return Command format of the task.
     */
    public static String getCommandFormat(String taskType, String timeFormat) {
        return String.format("Please follow the format:\n%s <task description> %s", taskType, timeFormat);
    }

    /**
     * Prints the given exception.
     *
     * @param dukeException The given exception.
     */
    public void showError(DukeException dukeException) {
        System.out.println(dukeException);
    }

    /**
     * Prints out error message indicating that file containing data is not found.
     */
    public static void showFileNotFoundError() {
        DukeException dukeException = new DukeException(
                "☹ OOPS!!! The file cannot be found. A new file has been created, please try again!");
        System.out.println(dukeException);
    }
}
