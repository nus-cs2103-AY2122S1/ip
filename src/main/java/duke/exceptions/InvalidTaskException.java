package duke.exceptions;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("There is no such task in your to-do list! ☹");
    }
}
