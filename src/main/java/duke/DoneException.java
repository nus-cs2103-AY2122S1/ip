package duke;

public class DoneException extends Exception {
    public DoneException() {
        super("There is nothing to mark as done here!");
    }

    public DoneException(String message) {
        super(message);
    }
}
