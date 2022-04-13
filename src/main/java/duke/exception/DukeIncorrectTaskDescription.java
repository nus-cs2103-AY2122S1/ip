package duke.exception;

import duke.task.Deadline;
import duke.task.Event;

/**
 * Represents the IncorrectTaskDescription Exception class
 * that is thrown when the user does not enter description
 * after wanting to add a task according to the format.
 */
public class DukeIncorrectTaskDescription extends DukeIncorrectInputs {
    private static final String PREFIX = "To log a";
    private static final String SUFFIX = ", you have to log the "
            + "\ndescription with the date like this:\n";

    /**
     * Constructor for the DukeIncorrectTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is a deadline.
     */
    public DukeIncorrectTaskDescription(Deadline deadline, Throwable err) {
        super(DukeIncorrectTaskDescription.PREFIX + " deadline"
                + DukeIncorrectTaskDescription.SUFFIX + "\t{deadline description} /by {yyyy-mm-dd}", err);
    }

    /**
     * Constructor for the DukeIncorrectTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is an event.
     */
    public DukeIncorrectTaskDescription(Event event, Throwable err) {
        super(DukeIncorrectTaskDescription.PREFIX + "n event"
                + DukeIncorrectTaskDescription.SUFFIX + "\t{event description} /at {yyyy-mm-dd}", err);
    }
}
