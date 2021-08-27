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
     */
    public abstract void printError();
}
