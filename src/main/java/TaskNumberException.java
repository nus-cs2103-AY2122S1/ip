package main.java;

public class TaskNumberException extends DukeException {
    public TaskNumberException() {
        super("The number is either not in the list or your input after the done or delete command is not an Integer! (pls do that)");
    }
}
