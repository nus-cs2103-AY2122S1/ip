package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks that are deadlines.
 */
public class Deadline extends Task {

    protected String by;
    protected String priority;
    protected LocalDate byDate;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected String[] monthArray = new  String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public Deadline(String description, String by, String priority) {
        super(description);
        this.by = by;
        this.byDate = LocalDate.parse(by, formatter);
        this.priority = priority;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return if the task is marked as done.
     */
    public boolean getIsDone() { return this.isDone;}

    /**
     * Retrieves the description of the task instance.
     *
     * @return the string description of the task
     */
    public String getDescription() { return this.description; }

    /**
     * Returns the date that the deadline should be done by
     *
     * @return a string of the date
     */
    public String getBy() {return this.by;}
    public String getPriority() {return this.priority;}

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + monthArray[byDate.getMonthValue()] + " " + byDate.getDayOfMonth() + " " + byDate.getYear() + ")" + " [Priority " + this.priority + "]";
    }
}