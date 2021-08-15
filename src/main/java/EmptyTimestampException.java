public class EmptyTimestampException extends DukeException {

    public EmptyTimestampException() {
        super("The timestamp of a Deadline/Event task cannot be empty!");
    }
}
