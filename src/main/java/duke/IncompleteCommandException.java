package duke;

public class IncompleteCommandException extends Exception {
    public IncompleteCommandException(String errorMessage) {
        super(errorMessage);
    }
}
