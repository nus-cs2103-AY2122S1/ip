package duke.task;

import java.text.ParseException;

/**
 * Class to store deadline. Subclass of task.
 *
 * @author marcuspeh
 * @version A-MoreOOP
 * @since 21 Aug 2021
 */
public class Deadlines extends Task {
    /**
     * Constructor for Deadline.
     *
     * @param task task to be stored.
     * @param dateTime deadTime for the task.
     * @throws ParseException Date / Time format is invalid.
     */
    public Deadlines(String task, String dateTime) throws ParseException {
        super(task, dateTime);
    }

    /**
     * Constructor for Deadline.
     *
     * @param task duke.task.Task to be stored.
     * @param dateTime deadTime for the task.
     * @param done Whether the task is done.
     */
    public Deadlines(String task, String dateTime, boolean done) throws ParseException {
        super(task, dateTime, done);
    }

    /**
     * Saves the task to the txt file.
     * Format is as follow: {@literal <}Type(D){@literal >} | {@literal <}Description{@literal >} |
     * {@literal <}Done{@literal >} | {@literal <}Datetime{@literal >}.
     *
     * @return string to save the txt file.
     */
    public String saveOutput() {
        return String.format("D | %s | %s | %s", super.getTask(),
                super.getIsDone() ? 1 : 0, super.getDateTime());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.getDateTime() + ")";
    }
}
