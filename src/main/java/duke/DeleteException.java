package duke;

public class DeleteException extends Exception {
    public DeleteException() {
        super("There is nothing to delete here! The format for deleting must be as such 'Delete 3'.");
    }

    public DeleteException(String message) {
        super(message);
    }

}
