/**
 * Represents the IncorrectTaskDescription Exception class
 * that is thrown when the user does not enter description
 * after wanting to add a task.
 */
public class DukeMissingTaskDescription extends DukeIncorrectInputs {
    private static final String prefix = "⚠️ OOPS!! The description of a";
    private static final String suffix = " cannot be empty. ⚠️";

    /**
     * Constructor for the DukeMissingTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is a todo.
     */
    public DukeMissingTaskDescription(ToDo todo, Throwable err) {
        super("\t" + DukeMissingTaskDescription.prefix + " todo" +
                DukeMissingTaskDescription.suffix, err);
    }

    /**
     * Constructor for the DukeMissingTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is a deadline.
     */
    public DukeMissingTaskDescription(Deadline deadline, Throwable err) {
        super("\t" + DukeMissingTaskDescription.prefix + " deadline" +
                DukeMissingTaskDescription.suffix, err);
    }

    /**
     * Constructor for the DukeMissingTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is an event.
     */
    public DukeMissingTaskDescription(Event event, Throwable err) {
        super("\t" + DukeMissingTaskDescription.prefix + "n event" +
                DukeMissingTaskDescription.suffix, err);
    }
}
