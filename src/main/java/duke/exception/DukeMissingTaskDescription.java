package duke.exception;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Represents the IncorrectTaskDescription Exception class
 * that is thrown when the user does not enter description
 * after wanting to add a task.
 */
public class DukeMissingTaskDescription extends DukeIncorrectInputs {
    private static final String PREFIX = "⚠️ OOPS!! The description of a";
    private static final String SUFFIX = " cannot be empty. ⚠️";

    /**
     * Constructor for the DukeMissingTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is a todo.
     */
    public DukeMissingTaskDescription(ToDo todo, Throwable err) {
        super(DukeMissingTaskDescription.PREFIX + " todo"
                + DukeMissingTaskDescription.SUFFIX, err);
    }

    /**
     * Constructor for the DukeMissingTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is a deadline.
     */
    public DukeMissingTaskDescription(Deadline deadline, Throwable err) {
        super(DukeMissingTaskDescription.PREFIX + " deadline"
                + DukeMissingTaskDescription.SUFFIX, err);
    }

    /**
     * Constructor for the DukeMissingTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is an event.
     */
    public DukeMissingTaskDescription(Event event, Throwable err) {
        super(DukeMissingTaskDescription.PREFIX + "n event"
                + DukeMissingTaskDescription.SUFFIX, err);
    }
}
