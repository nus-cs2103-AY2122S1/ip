package duke.exception;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Nee doesn't know what task you've done! \nAdd a description in the correct format.");
    }
}
