package side.exception;

public class TooManyIndexesException extends SideException {
    public TooManyIndexesException(String input) {
        super("I only have 1 hand, I can only " + input + " 1 at a time...");
    }
}
