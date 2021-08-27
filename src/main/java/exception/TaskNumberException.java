package main.java.exception;

/**
 * The TaskNumberException Exception is thrown when a task number referenced is not in the task list.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class TaskNumberException extends DukeException {

    /**
     * Exception when a done or delete command is followed by a number out of the list's range.
     */
    public TaskNumberException() {
        super("The number is either not in the list or your input after the done or delete command is not an Integer! (pls do that)");
    }
}
