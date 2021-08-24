import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task that requires a deadline that extends from Task
 *
 * @author: Wei Yangken
 */
public class Deadline extends Task{

    private String deadline;
    final static String split = " /by";

    /**
     *  Constructor to create an DEADLINE task
     * @param name Name of task
     * @param deadline Deadline of task
     */
    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if(this.isDone()) {
            return String.format("[D][X] %s (by: %s)", this.getName(), this.deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)", this.getName(), this.deadline);
        }
    }
}
