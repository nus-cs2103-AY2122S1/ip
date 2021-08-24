package data;

import java.util.Scanner;

/**
 * Class that deals with receiving input from the user and sending output to the user.
 */
public class Ui {
    /** Statement to show the user upon exit */
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\n" + "What can I do for you?";

    /** Statement to show the user upon running Duke */
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    /** Statement to show the user upon finding no saved file */
    private static final String MESSAGE_LOADING_ERROR = "No saved tasks were found.";

    private static Scanner userInput = new Scanner(System.in);

    private static final String DIVIDER = "===================================================";

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m + "\n");
        }
    }

    public void showWelcome() {
        showToUser(
            DIVIDER,
            MESSAGE_WELCOME,
            DIVIDER);
    }

    public void showGoodbye() {
        showToUser(MESSAGE_EXIT);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showLoadingError() {
        showToUser(
            DIVIDER,
            MESSAGE_LOADING_ERROR,
            DIVIDER);
    }

    public void showError(String e) {
        showToUser(e);
    }

    public void showMessage(String message) {
        showToUser(message);
    }

    public void showTasks(TaskList tasks) {
        showToUser("Here are your tasks:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(tasks.getTask(i).toString());
        }
    }

    /**
     * The following methods shouldIgnore, isCommentLine, readCommand and the string COMMENT_LINE_FORMAT_REGEX
     * were taken from addressbook-level2(https://github.com/se-edu/addressbook-level2) and edited slightly
     * to fit into iP
     */
    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the task back to the user.
     * @return task (full line) entered by the user
     */
    public String readCommand() {
        System.out.println("Enter task: ");
        String userInputLine = userInput.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(userInputLine)) {
            userInputLine = userInput.nextLine();
        }

        return userInputLine;
    }
}
