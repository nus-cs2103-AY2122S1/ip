package duke.exception;

public class InvalidTaskCommandException extends DukeException {
    public InvalidTaskCommandException() {
        super("Your command is not in the right format! Please refer to the user guide to view the" 
                + " the correct format for various types of task commands!");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
