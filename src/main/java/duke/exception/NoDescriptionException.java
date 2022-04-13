package duke.exception;

public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String string){
        super("OOPS! The description of " + string
                + " cannot be empty.");
    }
}
