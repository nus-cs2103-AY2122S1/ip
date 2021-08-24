import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task that requires event details and extends from Task
 *
 * @author: Wei Yangken
 */
public class Event extends Task{

    private String eventDetails;
<<<<<<< HEAD
    private static String taskCat = "event";
=======
    final static String split = " /at";
>>>>>>> branch-Level-8

    /**
     *  Constructor to create an EVENT task
     * @param name Name of task
     * @param eventDetails Location or time of event
     */
<<<<<<< HEAD
    Event(String name, String eventDetails, boolean isDone) {
        super(name, taskCat, isDone);
=======
    Event(String name, String eventDetails) {
        super(name);
>>>>>>> branch-Level-8
        this.eventDetails = eventDetails;
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if(this.isDone()) {
            return String.format("[E][X] %s (at: %s)", this.getName(), this.eventDetails);
        } else {
            return String.format("[E][ ] %s (at: %s)", this.getName(), this.eventDetails);
        }
    }

    /**
     * @return Gets event details of task
     */
    @Override
    public String getDetail() {
        return "/at " + this.eventDetails;
    }
}
