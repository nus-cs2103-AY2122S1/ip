import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents tasks with deadline.
 * 
 * @author Gordon Yit
 * @Version CS2103T, Semester 1
 */
public class Deadline extends Task {
    private final String TASK_MARKER = "D";
    private DateDue dateDue;
    /**
     * Class constructor for Deadline class.
     * 
     * @param description the task description.
     * @param by the time by which the task must be completed.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        dateDue = new DateDue(by);
    }

    /**
     * Print out the deadline task,
     * 
     * @return string format of the deadline task, 
     * consisting of the task marker "[D]", task description and deadline of the task.
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
