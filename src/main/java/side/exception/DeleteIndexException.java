package side.exception;

public class DeleteIndexException extends SideException {
    public DeleteIndexException() {
        super("Can't delete what isn't there...");
    }
}
