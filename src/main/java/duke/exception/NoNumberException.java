package duke.exception;

/**
 * Thrown when user does not enter task number to delete a task or mark a task as completed
 */
public class NoNumberException extends DukeException {
    private String error;
    /**
     * Constructs NoNumberException object
     *
     * @param msg error message
     */
    public NoNumberException(String msg) {
        super(msg);
        this.error = "OOPS!!! Please enter a task number!";
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.println(this.error);
    }

    @Override
    public String getError() {
        return this.error;
    }
}
