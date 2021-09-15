package duke.exceptions;

public class MissingDateException extends DukeException {
    /**
     * Constructor for <code>MissingDateException</code>
     */
    public MissingDateException() {
        super("Add a date to your Deadline or Event task!\n"
                + "For Deadline, insert a date using /by yyyy-mm-dd\n"
                + "For Event, insert a date using /at yyyy-mm-dd\n");
    }
}
