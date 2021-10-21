package duke.exception;

public class InvalidTaskNumberException extends DukeException {
    /**
     * Creates a new Invalid task number exception due to wrong input.
     */
    public InvalidTaskNumberException(int size) {
        super(size > 1
            ? "Please input a value between 1 and " + size
            : size == 1
            ? "You can only input the value 1"
            : "There are no tasks so far");
    }
}
