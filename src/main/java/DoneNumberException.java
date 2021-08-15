package main.java;

public class DoneNumberException extends DukeException {
    public DoneNumberException() {
        super("The number is either not in the list or your input after the done command is not an Integer! (pls do that)");
    }
}