package duke.exception;

/**
 * Thrown when user does not enter task number to delete a task or mark a task as completed
 */
public class NoNumberException extends DukeException {

    /**
     * Constructs NoNumberException object
     *
     * @param msg error message
     */
    public NoNumberException(String msg) {
        super(msg);
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! Please enter a task number!");
    }
}
