package duke.exception;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-( Please key in a valid task!");
    }
}
