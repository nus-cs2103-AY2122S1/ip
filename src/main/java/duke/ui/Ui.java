package duke.ui;

import duke.data.exception.DukeException;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Constructor for Ui
     */
    public Ui() {}

    /**
     * Returns greeting message
     *
     * @return greeting message
     */
    public String greet() {
        return "Hello, I'm Duke!\n\nWhat can I do for you?\nEnter 'help' to see the list of commands!";
    }

    /**
     * Returns goodbye message
     *
     * @return goodbye message
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns error message
     *
     * @param e the exception to print
     * @return error message
     */
    public String showErrorMessage(DukeException e) {
        return e.toString();
    }

    /**
     * Returns given message
     *
     * @param msg the message to print
     * @return given message
     */
    public String print(String msg) {
        return msg;
    }
}
