package duke;

public class DeleteException extends Exception {
    public DeleteException() {
        super("There is nothing to delete here!\n");
    }

    public DeleteException(String message) {
        super(message + "\n");
    }

}
