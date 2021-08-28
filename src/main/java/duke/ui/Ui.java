package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

import duke.commands.CommandResult;

/**
 * UI of the Duke bot. It interacts with the user, including reading inputs and displaying messages.
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETINGS = "Hello! What can I do for you?\n";
    private static final String GOODBYE = "See you later.";

    private Scanner in;
    private PrintStream out;

    /** Creates an instance of the UI with stdin as the input source and stdout as the output source. */
    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /** Displays the welcome message. */
    public void showWelcome() {
        out.println(LOGO);
        out.println(GREETINGS);
    }

    /** Displays the exit message. */
    public void showGoodbye() {
        out.println(GOODBYE);
    }

    /** Displays the error message when Duke cannot load existing tasks from file. */
    public void showLoadingError() {
        out.println("Could not load your saved tasks. Creating a new task list...");
    }

    /**
     * Displays the error message when Duke cannot save current tasks to file.
     *
     * @param msg The message of the error thrown.
     */
    public void showSavingError(String msg) {
        out.println("Error while saving tasks: \n" + msg);
    }

    /** Displays the horizontal separator. */
    public void showHorizontalLine() {
        out.println("-------------------------------------------------------------");
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
