/**
 * Represents the IncorrectTaskDescription Exception class
 * that is thrown when the user does not enter description
 * after wanting to add a task according to the format.
 */

public class DukeIncorrectTaskDescription extends DukeIncorrectInputs {
    private static final String prefix = "To log a";
    private static final String suffix = ", you have to log the " +
            "\n\tdescription with the date like this:\n";

    /**
     * Constructor for the DukeIncorrectTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is a deadline.
     */
    public DukeIncorrectTaskDescription(Deadline deadline, Throwable err) {
        super("\t" + DukeIncorrectTaskDescription.prefix + " deadline" +
                DukeIncorrectTaskDescription.suffix + "\t\t{deadline description} /by {date}", err);
    }

    /**
     * Constructor for the DukeIncorrectTaskDescription exception. Uses
     * runtime polymorphism to deduce that the task is an event.
     */
    public DukeIncorrectTaskDescription(Event event, Throwable err) {
        super("\t" + DukeIncorrectTaskDescription.prefix + "n event" +
                DukeIncorrectTaskDescription.suffix + "\t\t{event description} /at {date}", err);
    }
}
