import java.text.ParseException;

/**
 * Class to store deadline. Subclass of task.
 *
 * @author marcuspeh
 * @version Level-8
 * @since 20 Aug 2021
 */
public class Deadlines extends Task {
    /**
     * Constructor for Deadline.
     *
     * @param task task to be stored
     * @param dateTime deadTime for the task
     * @throws ParseException Date / Time format is invalid.
     */
    Deadlines(String task, String dateTime) throws ParseException {
        super(task, dateTime);
    }

    /**
     * Constructor for Deadline.
     *
     * @param task Task to be stored
     * @param dateTime deadTime for the task
     * @param done Whether the task is done
     */
    Deadlines(String task, String dateTime, boolean done) throws ParseException {
        super(task, dateTime, done);
    }

    /**
     * To save the task to the txt file.
     * Format is as follow: <Type(D)> | <Description> | <Done> | <Datetime>
     *
     * @return string to save the txt file
     */
    public String saveOutput() {
        return String.format("D | %s | %s | %s", super.getTask(), super.getIsDone() ? 1 : 0, super.getDateTime());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.getDateTime()  + ")";
    }
}
