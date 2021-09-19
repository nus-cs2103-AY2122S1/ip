package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

import duke.logic.commands.CommandResult;

/**
 * Text UI of the Duke bot. It interacts with the user through the CLI, reading inputs and displaying messages.
 */
public class TextUi {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String HORIZONTAL_LINE = "-------------------------------------------------------------";
    private static final String GREETINGS = "Hello! What can I do for you?\n";
    private static final String GOODBYE = "See you later.";
    private static final String LOAD_TASK_ERR_MSG = "\n  > Couldn't load your saved tasks. Creating a new task list...";
    private static final String SAVE_TASK_ERR_MSG = "Error while saving tasks: \n";

    private Scanner in;
    private PrintStream out;

    /** Creates an instance of the UI with stdin as the input source and stdout as the output source. */
    public TextUi() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /** Displays the welcome message. */
    public void showWelcomeMessage() {
        out.println(LOGO);
        out.println(GREETINGS);
    }

    /** Displays the exit message. */
    public void showGoodbyeMessage() {
        out.println(GOODBYE);
    }

    /**
     * Displays the error message when Duke cannot load existing tasks from file.
     *
     * @param msg The message of the error thrown.
     */
    public void showLoadingError(String msg) {
        out.println(msg + LOAD_TASK_ERR_MSG);
    }

    /**
     * Displays the error message when Duke cannot save current tasks to file.
     *
     * @param msg The message of the error thrown.
     */
    public void showSavingError(String msg) {
        out.println(SAVE_TASK_ERR_MSG + msg);
    }

    /** Displays the horizontal separator. */
    public void showHorizontalLine() {
        out.println(HORIZONTAL_LINE);
    }

    /** Displays a blank line. */
    public void showBlankLine() {
        out.println();
    }

    /**
     * Reads the next user command.
     *
     * @return The next user command, read as a whole line.
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Displays the command execution result.
     *
     * @param result The result to show to the user.
     */
    public void showResultToUser(CommandResult result) {
        out.println(result.getMessage());
    }

    /** Closes the input and output streams. */
    public void close() {
        in.close();
        out.close();
    }
}
