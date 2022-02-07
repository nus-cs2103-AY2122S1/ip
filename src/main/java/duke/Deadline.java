package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task with a specific deadline to be completed by.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructor for deadline task.
     *
     * @param description The string description of deadline.
     * @param deadline The LocalDate of a specific day.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    private String formatDate() {
        return deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    /**
     * Represents deadline task as a String object.
     *
     * @return String form of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formatDate() + ")";
    }

    /**
     * Creates a Deadline task from given task string.
     *
     * @return Deadline task.
     */
    public static Deadline create(String taskString, boolean isDone) {
        int timeStart = taskString.indexOf("by: ");
        String description = taskString.substring(7, timeStart - 2) + " ";
        String dateTime = taskString.substring(timeStart + 4, taskString.length() - 1);
        LocalDate date = LocalDate.parse(dateTime, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return new Deadline(description, date, isDone);
    }
}
