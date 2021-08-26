import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents tasks with specific timing.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */

public class Event extends Task {

    private DateDue dateDue;
    private final String TASK_MARKER = "E";
    
    /**
     * Class constructor for Event class.
     * 
     * @param description the task description.
     * @param timing the duration of the event.
     */
    public Event(String description, String timing) throws DateTimeParseException {
        super(description);
        this.dateDue = new DateDue(timing);
    }

    /**
     * Print out the event task,
     * 
     * @return string format of the event task, 
     * consisting of the task marker "E", task description and duration of the event.
     */
    @Override
    public String toString() {
        return String.format("| %s | %s | %s", TASK_MARKER, super.toString(), dateDue.toString());
    }

    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }

    /**
     * checks if given datetime matches the tasks date time.
     * 
     * @param dateTime date time to compare with.
     * @return true if the task date time matches the date time given.
     */
    @Override
    public boolean isSameDate(Object dateTime) {
        return this.dateDue.getLocalDate().equals(dateTime);
    }
}
