package duke.exception;

public class OutOfBoundException extends DukeException {
    public OutOfBoundException() {
        super("Task does not exist. Please send a correct task number ><");
    }
}
