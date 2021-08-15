public class EmptyDetailsException extends DukeException {

    public EmptyDetailsException() {
        super("The details of a Deadline/Event task cannot be empty!");
    }
}
