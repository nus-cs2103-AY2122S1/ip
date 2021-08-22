package side.exception;

public class NoIndexException extends SideException {

    public NoIndexException() {
        super("Can't do anything without task number...");
    }
}
