package duke.exception;

public class InvalidTaskCommandException extends DukeException {
    public InvalidTaskCommandException() {
        super("Your command is not in the right format! It should be <task type> <task description>/by " 
                + "<yyyy-mm-dd HHmm>");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
