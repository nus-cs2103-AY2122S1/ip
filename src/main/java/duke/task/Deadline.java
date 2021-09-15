package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a specific type of Task that have deadlines.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDateTime by;


    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline.
     */
    public Deadline(String description, String priority, String by) {
        super(description, priority);
        this.by = LocalDateTime.parse(by.trim(), DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    /**
     * Converts a Deadline into the format for storing.
     *
     * @return A String specific to a Deadline that follows the storing conventions for our data file.
     */
    @Override
    public String convertToFileFormat() {
        String temp = this.by.toString().replace("T", " ").replace(":", "");
        return String.format("D%s | %s", super.convertToFileFormat(), temp);
    }
}
