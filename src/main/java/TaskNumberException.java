package main.java;

public class TaskNumberException extends DukeException {

    /**
     * Exception thrown when a done or delete command is followed by a number out of the list's range
     */
    public TaskNumberException() {
        super("The number is either not in the list or your input after the done or delete command is not an Integer! (pls do that)");
    }
}
