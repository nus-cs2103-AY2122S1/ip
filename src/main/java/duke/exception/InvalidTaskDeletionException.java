package duke.exception;

public class InvalidTaskDeletionException extends DukeException {
    public InvalidTaskDeletionException() {
        super("OOPS! You are trying to delete a non-existent task!");
    }
}