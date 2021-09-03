package duke.exception;

/**
 * Throws exception specific to Duke
 */
public abstract class DukeException extends Exception{

    /**
     * Constructs DukeException object
     *
     * @param task task with error
     */
    public DukeException(String task) {
        super(task);
    }

    /**
     * Prints error message
     * @return
     */
    public abstract void printError();

    public abstract String getError();
}
