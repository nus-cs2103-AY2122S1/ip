package katheryne;

public class WrongIndexException extends KatheryneException {
    public WrongIndexException() {
        super("That's not a valid index...");
    }
}
