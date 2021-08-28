package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks that are deadlines.
 */
public class Deadline extends Task {

    protected String by;
    LocalDate byDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String[] monthArray = new  String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = LocalDate.parse(by, formatter);
    }

    /**
     * Checks if the task is marked as done.
     * @return if the task is marked as done.
     */
    public boolean getIsDone() { return this.isDone;}

    /**
     * Retrieves the description of the task instance.
     * @return the string description of the task
     */
    public String getDescription() { return this.description; }

    /**
     * Returns the date that the deadline should be done by
     * @return a string of the date
     */
    public String getBy() {return this.by;}

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + monthArray[byDate.getMonthValue()] + " " + byDate.getDayOfMonth() + " " + byDate.getYear() + ")";
    }
}