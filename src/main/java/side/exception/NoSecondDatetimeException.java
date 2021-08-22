package side.exception;

public class NoSecondDatetimeException extends SideException {
    public NoSecondDatetimeException() {
        super("Don't know when your event ends...");
    }
}

