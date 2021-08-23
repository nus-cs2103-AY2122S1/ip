package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {
    protected String by;
    protected String timeToDisplay;
    protected LocalDateTime deadline;

    /**
     * To create a task with a deadline
     * @param description The name/description of the task
     * @param by The deadline for the task (in the format dd/mm/yyyy hh:mm - in 24 hours format)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        int date = Integer.parseInt(by.substring(0, 2));
        int month = Integer.parseInt(by.substring(3, 5));
        int year = Integer.parseInt(by.substring(6, 10));
        int hour = Integer.parseInt(by.substring(11, 13));
        int min = Integer.parseInt(by.substring(14, 16));

        LocalDateTime taskDate = LocalDateTime.of(year, month, date, hour, min);
        this.timeToDisplay = taskDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        deadline = taskDate;
    }

    /**
     * Returns the deadline of a given deadline task
     * @return the deadline
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the deadline task as a string that is to be displayed to the user
     * @return the deadline task as a string that is to be displayed to the user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timeToDisplay + ")";
    }

    /**
     * Returns the deadline task as a string that is to be saved in user's hard disk
     * @return the deadline task as a string that is to be saved in user's hard disk
     */
    @Override
    public String toPrintToFile() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
