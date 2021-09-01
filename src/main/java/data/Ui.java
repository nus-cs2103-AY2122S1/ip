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
    public static String showToUser(String... message) {
        String displayedMessage = "";
        for (String m : message) {
            displayedMessage += (m + "\n");
        }
        return displayedMessage;
    }

    public void showWelcome() {
        showToUser(
            DIVIDER,
            MESSAGE_WELCOME,
            DIVIDER);
    }

    public static String showGoodbye() {
        return showToUser(MESSAGE_EXIT);
    }

    public String showLine() {
        return showToUser(DIVIDER);
    }

    public String showLoadingError() {
        return showToUser(
            DIVIDER,
            MESSAGE_LOADING_ERROR,
            DIVIDER);
    }

    public String showError(String e) {
        return showToUser(e);
    }

    public String showMessage(String message) {
        return showToUser(message);
    }

    public String showTasks(TaskList tasks) {
        String displayedTasks = "Here are your tasks:";
        for (int i = 0; i < tasks.getSize(); i++) {
            displayedTasks += (tasks.getTask(i).toString() + "\n");
        }
        return displayedTasks;
    }
}
