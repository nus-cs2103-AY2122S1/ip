/**
 * Represents the DukeNoSuchTask Exception class
 * that is thrown when the user wants to get a task
 * that does not exist within the list of tasks.
 */
public class DukeNoSuchTask extends DukeIncorrectInputs {
    /**
     * Constructor for the DukeNoSuchTask exception.
     */
    public DukeNoSuchTask(Throwable err) {
        super("\tThere is no such task saved in the list.", err);
    }
}
