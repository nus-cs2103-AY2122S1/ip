package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected String by;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the deadline task.
     *
     * @return The deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the formatted string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        localDateTime = new Parser().parseLocalDateTime(by);
        return "[D]" + super.toString() + " (by: " + localDateTime.format(dateTimeFormatter) + ")";
    }

    /**
     * Prints out deadline task.
     */
    @Override
    public void displayTask(){
        System.out.println("        " + toString());
    }

}

