package bobbybot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a bobbybot. Task that should be completed by a specified Date and Time
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     *
     * @param description description of task
     * @param by date and time that the task should be completed by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor used to load task from string
     * @param description description of task
     * @param by date and time that the task should be completed by
     * @param isDone boolean flag if task is done
     */
    public Deadline(String description, String by, boolean isDone, DateTimeFormatter formatter) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(description + " could not loaded from database");
        }
        this.isDone = isDone;
    }

    /**
     * Getter for string in save-friendly format
     */
    @Override
    public String getSaveFormatString() {
        int isDoneInt = isDone ? 1 : 0;
        return ("D," + isDoneInt + "," + description + ","
                + by.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
