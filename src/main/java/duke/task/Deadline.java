package duke.task;

import duke.DukeException;

/**
 * A Task with a deadline.
 * @author Thomas Hogben
 */
public class Deadline extends DateAndTimeTask {
    private static String splitterKey = " /by ";

    /**
     * @param description The input string to create the Task.
     *                    Format: "[description] /by yyyy-mm-dd hhmm" (24h time)
     */
    public Deadline(String description) throws DukeException {
        super(description, splitterKey);
    }

    /**
     * @param description The input string to create the Task.
     *                    Format: "[description] /by yyyy-mm-dd hhmm" (24h time)
     * @param isDone Whether the task is done.
     */
    public Deadline(String description, String dateAndTime, boolean isDone) throws DukeException {
        super(description, dateAndTime, isDone);
    }

    @Override
    public String getSave() {
        return "D" + super.getSave();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
