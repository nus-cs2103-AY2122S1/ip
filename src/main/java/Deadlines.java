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


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.getDateTime()  + ")";
    }
}
