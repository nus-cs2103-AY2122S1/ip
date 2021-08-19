public class InvalidTaskIDException extends DukeException {
    public InvalidTaskIDException() {
        super("I couldn't find a task with that ID. Please check again!");
    }
}
